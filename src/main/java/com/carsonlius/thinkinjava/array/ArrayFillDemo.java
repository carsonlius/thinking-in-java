package com.carsonlius.thinkinjava.array;

import java.util.Arrays;

public class ArrayFillDemo {
    public static void main(String[] args) {
        boolean[] booleans = new boolean[8];
        Arrays.fill(booleans, true);
        System.out.println(Arrays.toString(booleans));

        String[] strings = new String[5];
        Arrays.fill(strings, 2,4, "hello world");
        System.out.println(Arrays.toString(strings));
        Arrays.fill(strings, 1,3, "None");
        System.out.println(Arrays.toString(strings));


    }
}
