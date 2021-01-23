package com.carsonlius.thinkinjava.threads;

import javax.annotation.security.RunAs;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Count {
    private int count = 0;

    private Random random = new Random(47);

    public synchronized int increment(){
        int temp = count;
        if (random.nextBoolean()) {
            Thread.yield();
        }

        return count = ++temp;
    }

    public synchronized int value(){
        return count;
    }
}

class Entrance implements Runnable {
    private static Count count = new Count();

    private static List<Entrance> entrances = new ArrayList<>();

    private static volatile boolean canceled = false;

    public static void setCanceled(boolean canceled) {
        Entrance.canceled = canceled;
    }

    private final int id;

    private int number;

    public Entrance(int id) {
        this.id = id;

        entrances.add(this);
    }

    @Override
    public void run() {
        while (!canceled) {
            synchronized (this) {
                number++;
            }

            System.out.println(this + " Total" +  count.increment());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("sleep interrupted");
            }
        }

        System.out.println("Stopping " + this);

    }

    @Override
    public String toString() {
        return "Entrance{" +
                "id=" + id +
                "} number:" + getValue();
    }

    private synchronized int getValue(){ return number;}

    public static int sumEntrances(){
        int sum = 0;
        for (int i = 0; i < entrances.size(); i++) {
            sum += entrances.get(i).getValue();
        }

        return sum;
    }

    public static int getTotalCount(){
        return count.value();
    }
}

public class OrnametalGarden {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Entrance(i));
        }

        executorService.shutdown();
        Thread.sleep(3000);

        Entrance.setCanceled(true);

        if(!executorService.awaitTermination(250, TimeUnit.MILLISECONDS)){
            System.out.println("25毫秒内 有些任务未完全停止");
        }

        System.out.println("Count Total:" + Entrance.getTotalCount());

        System.out.println(" Sum:" + Entrance.sumEntrances());

    }

}
