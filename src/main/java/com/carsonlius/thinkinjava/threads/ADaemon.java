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

    public static void main(String[] args) throws InterruptedException {
        ADaemon aDaemon = new ADaemon();
        aDaemon.test();
        new LiftOff();

    }

    private void test() throws InterruptedException {
        wait();
    }
}
