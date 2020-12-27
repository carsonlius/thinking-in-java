package com.carsonlius.thinkinjava.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ContainerComparion {
    public static void main(String[] args) {
        int[][][] ages = new int[2][3][4];
        System.out.println(Arrays.deepToString(ages));
        System.out.println(ages[0][0][0]);

        int[] a;



        BaseInstance[] baseInstances = new BaseInstance[10];

        for (int i = 0; i <5 ; i++) {
            baseInstances[i] = new BaseInstance();
        }
        System.out.println(Arrays.toString(baseInstances));

        List<BaseInstance> baseInstanceList = new ArrayList<>(10);
        for (int i = 0; i <5 ; i++) {
            baseInstanceList.add(new BaseInstance());
        }


        System.out.println(baseInstanceList);
    }
}
