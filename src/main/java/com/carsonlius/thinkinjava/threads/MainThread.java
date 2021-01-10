package com.carsonlius.thinkinjava.threads;

public class MainThread {
    public static void main(String[] args) {
//        LiftOff liftOff = new LiftOff();
//        liftOff.run();

        Thread thread = new Thread(new LiftOff());
        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());

        thread.start();

        for (int i = 0; i <5 ; i++) {
//            new Thread(new LiftOff()).start();

        }

    }
}
