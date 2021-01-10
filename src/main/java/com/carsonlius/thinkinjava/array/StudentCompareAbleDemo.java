package com.carsonlius.thinkinjava.array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class StudentCompareAbleDemo implements Comparable<StudentCompareAbleDemo> {
    @Override
    public String toString() {
        return "{" +
                "age=" + age +
                '}';
    }

    private int age;

    public StudentCompareAbleDemo(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(StudentCompareAbleDemo o) {
        return Integer.compare( o.age,age);
    }

    public static void main(String[] args) {
        StudentCompareAbleDemo[] studentCompareAbleDemos = new StudentCompareAbleDemo[10];
        Random random = new Random(47);
        for (int i = 0; i <10 ; i++) {
            studentCompareAbleDemos[i] = new StudentCompareAbleDemo(random.nextInt(100));
        }
        System.out.println(Arrays.deepToString(studentCompareAbleDemos));

        Arrays.sort(studentCompareAbleDemos);
        System.out.println(Arrays.toString(studentCompareAbleDemos));
    }
}
