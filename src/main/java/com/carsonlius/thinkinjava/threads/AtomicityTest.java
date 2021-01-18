package com.carsonlius.thinkinjava.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AtomicityTest implements Runnable {
    private volatile int currentValue;
    private  int getCurrentValue(){
        return currentValue;
    }

    private synchronized void evenIncrement(){
        currentValue++;
        currentValue++;
    }

    @Override
    public void run() {
        while (true) {
            evenIncrement();
        }
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        AtomicityTest atomicityTest = new AtomicityTest();
        service.execute(atomicityTest);

        while (true) {
            int value = atomicityTest.getCurrentValue();
            if (value%2 != 0) {
                System.out.println("val " + value);
                System.exit(0);
            }
        }
    }
}
