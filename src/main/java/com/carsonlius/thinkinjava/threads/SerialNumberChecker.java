package com.carsonlius.thinkinjava.threads;


import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class CircularSet {
    private int len;
    private int[] storage;
    private int index = 0;

    public CircularSet(int len) {
        storage = new int[len];
        for (int i = 0; i < storage.length; i++) {
            storage[i] = -1;
        }

        this.len = len;
    }

    public synchronized void add(int val){
        storage[index++] = val;

        // 循环
        index = index%len;
    }

    public  synchronized boolean contains(int val){
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == val) {
                return true;
            }
        }

        return false;
    }
}

public class SerialNumberChecker {
    private static final int SIZE = 10;
    private static CircularSet circularSet = new CircularSet(1000);
    private static ExecutorService service = Executors.newCachedThreadPool();

    static class SerialChecker implements Runnable {
        @Override
        public void run() {
            while (true) {
                int serial = SerialNumberGenerator.nextSerialValue();
                if (circularSet.contains(serial)) {
                    System.out.println("重复的val:" + serial);
                    return;
                }

                circularSet.add(serial);
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < SIZE; i++) {
            service.execute(new SerialChecker());
        }

        System.out.println(Arrays.toString(args));
    }

}
