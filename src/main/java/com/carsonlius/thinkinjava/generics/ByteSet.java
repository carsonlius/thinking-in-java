package com.carsonlius.thinkinjava.generics;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ByteSet {
    public static void main(String[] args) {
        Byte[] bytes = {1,2,3,4,5};
        Set<Byte> byteSet = new HashSet<>(Arrays.asList(bytes));
        System.out.println(byteSet);



    }

}
