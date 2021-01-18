package com.carsonlius.thinkinjava.threads;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AttemptLocking {
    private Lock lock = new ReentrantLock();
    public void unTimed(){
        boolean captured = lock.tryLock();
        try {
            System.out.println("unTimed tryLock method result:" + captured);

        }finally {
            if (captured) {
                lock.unlock();
            }
        }
    }

    public void timed(){
        boolean captured = false;

        try {
            captured = lock.tryLock(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("timed tryLock :" + captured);
        } finally {
          if (captured) {
              lock.unlock();

          }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AttemptLocking attemptLocking = new AttemptLocking();
        attemptLocking.timed();
        attemptLocking.unTimed();

        new Thread() {
            @Override
            public void run() {
                 attemptLocking.lock.lock();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    attemptLocking.lock.unlock();
                }
                System.out.println("acquire -----------------> ");
            }
        }.start();

        Thread.yield();
        Thread.sleep(1000);
        attemptLocking.timed();
        attemptLocking.unTimed();
    }
}
