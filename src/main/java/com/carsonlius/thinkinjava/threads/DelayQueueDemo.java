package com.carsonlius.thinkinjava.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeUnit.*;

class DelayedTask implements Runnable, Delayed {
    private static int count = 0;
    private final int id = count++;

    private final int delta;

    private final long trigger;

    private static List<DelayedTask> delayedTaskList = new ArrayList<>();

    public DelayedTask(int delta) {
        this.delta = delta;
        this.trigger = System.nanoTime() + NANOSECONDS.covert(delta, MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }

    @Override
    public void run() {

    }

    @Override
    public long getDelay(TimeUnit unit) {
        return 0;
    }

}


public class DelayQueueDemo {
    public static void main(String[] args) {
        System.out.println(System.nanoTime());
    }
}
