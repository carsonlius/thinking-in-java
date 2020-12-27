package com.carsonlius.thinkinjava.array;

public class BaseInstance {
    private static long counter;

    private  final long id;

    static {
        System.out.println("静态区域被触发");
    }

    public BaseInstance() {
        id = counter++;
    }

    @Override
    public String toString() {
        return "BaseInstance{} counter:" + id;
    }
}
