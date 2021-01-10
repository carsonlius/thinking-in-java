package com.carsonlius.thinkinjava.array;

public class Person {
    private int age;

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "{" +
                "age=" + age +
                '}';
    }

    public Person(int age) {
        this.age = age;
    }
}
