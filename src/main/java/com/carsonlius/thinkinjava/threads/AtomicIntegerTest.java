package com.carsonlius.thinkinjava.threads;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest implements Runnable {
    private AtomicInteger i = new AtomicInteger(0);

    private int getValue(){
        return i.get();
    }

    private void evenIncrement(){
        i.addAndGet(2);
    }

    @Override
    public void run() {
        while (true) {
            evenIncrement();
        }
    }

    public static void main(String[] args) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("10秒钟没有生成奇数" );
                System.exit(100);
            }
        }, 10000);

        ExecutorService executorService = Executors.newCachedThreadPool();
        AtomicIntegerTest atomicIntegerTest = new AtomicIntegerTest();
        executorService.execute(atomicIntegerTest);

        while (true) {
            int value = atomicIntegerTest.getValue();
            if (value%2 != 0) {
                System.out.println("产生非偶数");
                System.exit(0);

            }


        }
    }
}
