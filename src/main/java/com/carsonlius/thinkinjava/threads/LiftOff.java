package com.carsonlius.thinkinjava.threads;

import com.carsonlius.thinkinjava.enums.Light;

public class LiftOff implements Runnable {
    private static int count = 0;
    protected final int id = count++;
    protected int cutDown = 10;


    public LiftOff() {
    }

    LiftOff(int cutDown) {
        this.cutDown = cutDown;
    }

    @Override
    public void run() {
        while (cutDown-- > 0) {

            System.out.println("#Id:" + id + (cutDown > 0 ? "剩余" + cutDown + "秒" : "") + "起飞!🛫️");
            Thread.yield();
        }
    }

}
