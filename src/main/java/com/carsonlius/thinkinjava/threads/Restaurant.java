package com.carsonlius.thinkinjava.threads;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Meal {
    private final int orderNum;

    public Meal(int orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "orderNum=" + orderNum +
                '}';
    }
}


class WaitPerson implements Runnable {
    private Restaurant restaurant;

    public WaitPerson(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // 这里加锁是为了防止错过
                synchronized (this) {
                    // 等待菜
                    while (restaurant.meal == null) {
                        wait();
                    }
                }

                // 上菜
                System.out.println("WaitPerson get " + restaurant.meal);

                // 因为需要通知厨师做菜，所以首先要获取厨师对应锁
                synchronized (restaurant.chef) {
                    restaurant.meal = null;
                    restaurant.chef.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("WaitPerson is interrupted");
        }
    }
}


class Chef implements Runnable {
    private int orderNum = 0;
    private Restaurant restaurant;

    public Chef(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // 等待服务员把菜端走
                synchronized (this) {
                    while (restaurant.meal != null) {
                        wait();
                    }
                }

                // 只做10菜
                if (orderNum++ == 10) {
                    System.out.println("out of food closing");

                    // 终结所有的线程
                    restaurant.executorService.shutdownNow();
                }

                System.out.println("Order Up!");
                // 因为通知服务员，所以需要获取服务员的🔒
                synchronized (restaurant.waitPerson) {
                    restaurant.meal = new Meal(orderNum);
                    restaurant.waitPerson.notifyAll();
                }
//                TimeUnit.MILLISECONDS.sleep(200);
            }

        } catch (InterruptedException e) {
            System.out.println("Chef is interrupted");
        }
    }
}

public class Restaurant {
    Meal meal;

    Chef chef = new Chef(this);

    WaitPerson waitPerson = new WaitPerson(this);

    ExecutorService executorService = Executors.newCachedThreadPool();

    public Restaurant() {
        executorService.execute(waitPerson);
        executorService.execute(chef);
    }

    public static void main(String[] args) {
        new Restaurant();
    }

}
