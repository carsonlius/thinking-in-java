package com.carsonlius.thinkinjava.array;

import java.util.Arrays;

public class SystemArrayCopy {
    public static void main(String[] args) {
        int[] i = new int[10];
        int[] j = new int[5];
        Arrays.fill(i, 33);
        Arrays.fill(j, 5);
        int start = j.length/2;

        System.arraycopy(i, 0, j, start, start);
        System.out.println("j" + Arrays.toString(j));
    }
}
