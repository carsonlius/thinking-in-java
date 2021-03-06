package com.carsonlius.thinkinjava.threads;

public class MultiLock {
    public synchronized void f1(int count){
        if (count-- >0) {
            System.out.println("f1 calling f2 with count:" + count);
        }
    }

    public synchronized  void f2(int count){
        if (count--  > 0) {
            System.out.println("f2 calling f1 with count:" + count);
        }
    }

    public static void main(String[] args) {
        MultiLock multiLock = new MultiLock();
        new Thread(){
            @Override
            public void run() {
                multiLock.f1(10);
            }
        }.start();
    }
}
