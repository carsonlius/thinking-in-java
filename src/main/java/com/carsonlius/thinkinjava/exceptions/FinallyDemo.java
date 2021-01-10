package com.carsonlius.thinkinjava.exceptions;

import java.util.Random;

public class FinallyDemo {
    private static int age = 0;
    public static void main(String[] args) {
        System.out.println(test());
    }

    private static int test(){
        try {
            Random random = new Random(47);
            int randomInt = random.nextInt(100);
                throw new ErrorParams("错误的参数");
        }catch (ErrorParams errorParams) {
            System.out.println(errorParams + errorParams.getMessage());
            age =  200;
            return age *10;
        } finally {
            System.out.println("最终执行!");
            age = 99;
            age *=100;
            return age;
        }
    }
}
