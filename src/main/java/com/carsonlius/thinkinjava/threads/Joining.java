package com.carsonlius.thinkinjava.threads;

class Sleeper extends Thread  {
    private int sleepTime;
    public Sleeper(String name, int sleepTime) {
        super(name);
        this.sleepTime = sleepTime;
        start();
    }

    @Override
    public void run() {
        try {
            sleep(sleepTime);
        } catch (InterruptedException e) {
            System.out.println(getName() + " is interrupted and isInterrupted :" + isInterrupted());
            return;
        }

        System.out.println(getName() + " is waked!");
    }
}

class Joiner extends Thread {
    private Thread sleeper;
    public Joiner(String name, Thread sleeper) {
        super(name);
        this.sleeper = sleeper;
        start();
    }

    @Override
    public void run() {
        try {
            sleeper.join();
        } catch (InterruptedException e) {
            System.out.println(getName() + "interrupted! and status is " + isInterrupted());
        }
        System.out.println(getName() + " join completed!");
    }
}


public class Joining {
    public static void main(String[] args) {
        Sleeper sleeper = new Sleeper("睡眠线程", 3500);
        Joiner joiner = new Joiner("Joiner 线程", sleeper);

        Sleeper sleeper1 = new Sleeper("睡眠且被打断的线程", 3500);
        Joiner joiner1 = new Joiner("Joiner线程 断掉",sleeper1);
        sleeper1.interrupt();

    }

}
