package com.carsonlius.thinkinjava.threads;

public class ThreadMethod {
    private Thread t;
    private int cutDown = 5;
    private String name;
    public ThreadMethod(String name) {
        this.name = name;
    }

    private void runTask() {
        t = new Thread(name) {
            @Override
            public void run() {
                while (cutDown-- > 0) {
                    System.out.println(Thread.currentThread() + " : " + cutDown);
                }
            }
        };

        t.start();
    }

    public static void main(String[] args) {
        ThreadMethod threadMethod = new ThreadMethod("异步方法");
        threadMethod.runTask();
    }
}
