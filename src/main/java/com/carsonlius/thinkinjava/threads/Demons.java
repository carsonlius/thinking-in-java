package com.carsonlius.thinkinjava.threads;
class Daemon implements Runnable {

    private Thread[] threads = new Thread[10];

    @Override
    public void run() {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new DaemonSpawn());
            threads[i].start();

            System.out.println("threads[" + i + "].getState=" + threads[i].getState());
        }

        for (int i = 0; i < threads.length; i++) {
            System.out.println("threads[" + i + "].isDaemon()=" + threads[i].isDaemon());
        }

        while (true) {
            Thread.yield();
        }
    }
}

class DaemonSpawn implements Runnable {
    @Override
    public void run() {
        while (true) {
            Thread.yield();
        }
    }
}

public class Demons {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Daemon());
        thread.setDaemon(true);
        thread.start();

        System.out.println("thread start and isDaemon" + thread.isDaemon());

        Thread.sleep(3000);
    }
}
