package com.carsonlius.thinkinjava.threads;

import java.util.concurrent.TimeUnit;

public class SimpleDemos implements Runnable {
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
        for (int i = 0; i <10 ; i++) {
            Thread daemonThread = new Thread(new SimpleDemos());
            daemonThread.setDaemon(true);
            daemonThread.start();
        }

        TimeUnit.MILLISECONDS.sleep(301);
        System.out.println("main ending ---" + Thread.currentThread());

    }
}
