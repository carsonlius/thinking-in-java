package com.carsonlius.thinkinjava.threads;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

class Accessor implements Runnable {
    private final int id;

    public Accessor(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            ThreadLocalVariableHolder.increment();
            System.out.println(this);
            Thread.yield();
        }
    }

    @Override
    public String toString() {
        return "Accessor{" + id+ "}" + ThreadLocalVariableHolder.get();
    }
}

public class ThreadLocalVariableHolder {
    private static ThreadLocal<Integer> value = new ThreadLocal<Integer>(){
        private Random random = new Random(47);

        @Override
        public synchronized Integer initialValue(){
            return random.nextInt(10000);
        }
    };

    public static void increment(){
        value.set(value.get()+1);
    }

    public static Integer get(){
        return value.get();
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread  = new Thread(r);
                thread.setDaemon(true);
                return thread;
            }
        });

        for (int i = 0; i < 3; i++) {
            executorService.execute(new Accessor(i));
        }

        executorService.shutdown();
        Thread.sleep(1);

    }
}
