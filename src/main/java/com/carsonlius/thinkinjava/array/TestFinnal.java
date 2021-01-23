package com.carsonlius.thinkinjava.array;

public class TestFinnal {
    public static void main(String[] args) {
        System.out.println(testInt());
    }

    public static int testInt(){

        int a;
        try {

            return 200;

        } catch (RuntimeException e) {
            e.printStackTrace();

        }finally {
            System.out.println("hello world");
            a= 100;
            return 300;
        }

//        System.out.println("not reach");
//        return a;
    }
}
