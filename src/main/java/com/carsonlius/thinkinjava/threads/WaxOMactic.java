package com.carsonlius.thinkinjava.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Car {
    private boolean waxOn = false;

    /**
     * 打蜡
     */
    public synchronized void waxed() {
        waxOn = true;

        // 通知抛光
        notifyAll();
    }

    /**
     * 抛光
     */
    public synchronized void buff() {
        //抛光
        waxOn = false;

        // 通知继续打蜡
        notifyAll();
    }

    /**
     * 等待打蜡
     */
    public synchronized void waitWaxed() throws InterruptedException {

        // while 防止其他不相关的任务释放了锁 被当前任务捕捉到了，但是此时还没有打蜡
        while (waxOn == false) {
            wait();
        }
    }

    /**
     * 等待抛光
     */
    public synchronized void waitBuffing() throws InterruptedException {
        while (waxOn) {
            wait();
        }
    }
}

/**
 * 打蜡任务
 */
class WaxOn implements Runnable {
    private Car car;

    public WaxOn(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {

                // 打蜡
                car.waxed();
                TimeUnit.MILLISECONDS.sleep(200);
                System.out.println("Wax On!");
                // 等待抛光
                car.waitBuffing();
            }

        } catch (InterruptedException e) {
            System.out.println("WaxOn 捕捉到打断异常");
        }

        System.out.println("Ending Wax On Task!");
    }
}

class WaxOff implements Runnable {
    private Car car;

    public WaxOff(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // 抛光
                car.buff();
                TimeUnit.MILLISECONDS.sleep(200);

                System.out.println("Wax Off!");
                // 等待打蜡
                car.waitWaxed();
            }
        } catch (InterruptedException e) {
            System.out.println("WaxOff 捕捉到打断异常");
        }

        System.out.println("Ending Wax Off Task!");
    }
}

public class WaxOMactic {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Car car = new Car();
        executorService.execute(new WaxOff(car));
        executorService.execute(new WaxOn(car));

        // 睡眠5秒钟
        TimeUnit.SECONDS.sleep(5);

        executorService.shutdownNow();

    }
}
