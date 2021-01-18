package com.carsonlius.thinkinjava.threads;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicEvenGenerator extends IntGenerator {
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public int next() {
        return atomicInteger.getAndAdd(2);
    }

    public static void main(String[] args) {
        new Timer().schedule(new TimerTask(){
            @Override
            public void run() {
                EvenChecker.test(new AtomicEvenGenerator());
            }

            @Override
            public boolean cancel() {
                System.out.println("取消");
                return super.cancel();
            }
        }, 1000);
    }
}
