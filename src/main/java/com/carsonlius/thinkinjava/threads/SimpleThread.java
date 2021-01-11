package com.carsonlius.thinkinjava.threads;

import javax.swing.*;

public class SimpleThread extends Thread {
    private static int threadCount = 0;
    private int cutDown = 5;

    public SimpleThread() {
        super(Integer.toString(++threadCount));
        start();
    }

    @Override
    public String toString() {
        return "SimpleThread " + getName()+ "{" +
                "cutDown=" + cutDown +
                '}';
    }

    @Override
    public void run() {
        while (--cutDown > 0) {
            System.out.println(this);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new SimpleThread();
        }
    }
}
