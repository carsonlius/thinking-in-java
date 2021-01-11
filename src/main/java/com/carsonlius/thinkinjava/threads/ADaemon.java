package com.carsonlius.thinkinjava.threads;

public class ADaemon implements Runnable {
    @Override
    public void run() {
        System.out.println("start ADaemon");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("This should always run? ");
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new ADaemon());
        thread.setDaemon(true);
        thread.start();

    }
}
