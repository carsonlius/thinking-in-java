package com.carsonlius.thinkinjava.threads;


import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class SleepBlocked implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("SleepThread Interrupted");

        }

        System.out.println("Exited SleepThread Block!");
    }
}

class IoBlocked implements Runnable {
    private InputStream in;

    public IoBlocked(InputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        System.out.println("Waiting read");
        try {
//            Thread.sleep(1000000);

            in.read();
        } catch (  IOException e) {
//            System.out.println(Thread.currentThread().getState());
//            System.out.println(Thread.currentThread().isInterrupted());
            System.out.println("期望被中断");
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("读阻滞被打断了");

            } else {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Exiting IoBlocked.run");
    }
}


class SynchronizedBlocked implements Runnable {
    private synchronized void f(){
        while (true) {
            Thread.yield();
        }
    }

    public SynchronizedBlocked() {
        new Thread(){
            @Override
            public void run() {
                f();
            }
        }.start();
    }

    @Override
    public void run() {
        System.out.println("try to call f" + Thread.currentThread().getName());
        f();
        System.out.println("Exiting SynchronizedBlock");
    }
}





public class Interrupting {
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    static void test(Runnable r) throws InterruptedException {
        Future<?> future = executorService.submit(r);
        Thread.sleep(100);
        System.out.println("Interrupting " + r.getClass().getCanonicalName());

        future.cancel(true);
        System.out.println("Interrupt sent to " + r.getClass().getName());
    }

    public static void main(String[] args) throws InterruptedException {
//        test(new SleepBlocked());
        test(new IoBlocked(System.in));
//        test(new SynchronizedBlocked());
    }
}
