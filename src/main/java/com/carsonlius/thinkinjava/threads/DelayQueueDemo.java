package com.carsonlius.thinkinjava.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.TimeUnit.*;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

class DelayedTask implements Runnable, Delayed {
    private static int count = 0;
    private final int id = count++;

    private final int delta;

    private final long trigger;

    private static List<DelayedTask> delayedTaskList = new ArrayList<>();

    public DelayedTask(int delta) {
        this.delta = delta;
        this.trigger = System.nanoTime() + NANOSECONDS.convert(delta, MILLISECONDS);

        delayedTaskList.add(this);
    }

    @Override
    public int compareTo(Delayed o) {
        DelayedTask that = (DelayedTask) o;
        if (that.trigger > trigger) {
            return -1;
        } else if (that.trigger == trigger) {
            return 0;
        }
        return 1;
    }

    @Override
    public void run() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return String.format("[%1$-4d]", delta) + " Task " + id;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(trigger - System.nanoTime(), NANOSECONDS);
    }

    public String summary(){
        return "(id:" +id+") : " + delta;
    }

    public static class EndSentinel extends DelayedTask {
        private ExecutorService exec;

        public EndSentinel(int delta, ExecutorService executorService) {
            super(delta);
            exec = executorService;
        }

        @Override
        public void run() {
            for (int i = 0; i < delayedTaskList.size(); i++) {
                System.out.println(delayedTaskList.get(i).summary());
            }
            System.out.println(this + " calling shutdownNow()");
            exec.shutdownNow();
        }
    }
}

class DelayedTaskCustomer implements Runnable {
    private DelayQueue<DelayedTask> q;

    public DelayedTaskCustomer(DelayQueue<DelayedTask> q) {
        this.q = q;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                q.take().run();
            }
        }catch (InterruptedException e) {
            System.out.println("DelayedTaskCustomer Exist via Interrupted");
        }
    }
}


public class DelayQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Random random = new Random(47);
        DelayQueue<DelayedTask> queue = new DelayQueue<>();
        for (int i = 0; i < 20; i++) {
            queue.add(new DelayedTask(random.nextInt(5000)));
        }

        queue.add(new DelayedTask.EndSentinel(5000, executorService));
        // 开启执行
        executorService.execute(new DelayedTaskCustomer(queue));
    }
}
