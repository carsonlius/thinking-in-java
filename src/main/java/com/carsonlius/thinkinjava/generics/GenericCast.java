package com.carsonlius.thinkinjava.generics;

import java.util.ArrayList;

public class GenericCast {
    public static final int SIZE = 10;

    public static void main(String[] args) {
        FixedSizeStack<String> stringFixedSizeStack = new FixedSizeStack<>(SIZE);
        String[] strings = "A B C D E F G H J K".split(" ");
        for (String string : strings) {
            stringFixedSizeStack.push(string);
        }

        for (int i = 0; i < SIZE; i++) {
            String s = stringFixedSizeStack.pop();
            System.out.println(s);
        }
    }
}
