package com.carsonlius.thinkinjava.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DaemonFromFactory implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + " " + this);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool(new DaemonThreadFactory());

        for (int i = 0; i <10 ; i++) {
            executorService.execute(new DaemonFromFactory());
        }

        executorService.shutdown();
        System.out.println("all daemons start");
        Thread.sleep(500);

        System.out.println("===============> END <===============");


    }
}
