package com.carsonlius.thinkinjava.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SleepingTask extends LiftOff {
    @Override
    public void run() {
        while (cutDown-- > 0) {
            System.out.println("#Id:" + id + (cutDown > 0 ? "剩余" + cutDown + "秒" : "") + "起飞!🛫️");
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i <5 ; i++) {
            executorService.execute(new SleepingTask());
        }
        executorService.shutdown();
        System.out.println(executorService.isShutdown());
    }
}
