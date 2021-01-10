package com.carsonlius.thinkinjava.enums;

enum Color {
    RED, BLACK, WHITE
}

public class EnumDemo {
    public static void main(String[] args) {
        System.out.println(Color.values());

        for (Color value : Color.values()) {
            System.out.println(value + " ordinal: " + value.ordinal());
            System.out.println(value.compareTo(Color.RED));
            System.out.println(value.name());
            System.out.println(value.getDeclaringClass());
            System.out.println(value.getClass());
            System.out.println("----------");
        }
    }
}
