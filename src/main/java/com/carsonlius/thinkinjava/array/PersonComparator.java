package com.carsonlius.thinkinjava.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class PersonComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        return Integer.compare(o1.getAge(), o2.getAge());
    }

    public static void main(String[] args) {
        Person[] persons = new Person[10];
        Random random = new Random(47);
        for (int i = 0; i <10 ; i++) {
            persons[i] = new Person(random.nextInt(100));
        }

        Arrays.sort(persons, new PersonComparator());
        System.out.println(Arrays.toString(persons));
    }
}
