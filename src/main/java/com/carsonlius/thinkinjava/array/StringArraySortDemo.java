package com.carsonlius.thinkinjava.array;

import java.util.Arrays;

public class StringArraySortDemo {
    public static void main(String[] args) {
        String[] strings = new String[4];
        strings[0] = "Hello";
        strings[1] = "hello";
        strings[2] = "ab";
        strings[3] = "Ab";
        Arrays.sort(strings, String.CASE_INSENSITIVE_ORDER);

        System.out.println(Arrays.toString(strings));
    }
}
