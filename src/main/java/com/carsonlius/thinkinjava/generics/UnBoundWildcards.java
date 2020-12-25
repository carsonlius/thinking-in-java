package com.carsonlius.thinkinjava.generics;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UnBoundWildcards {
    static List list1;
    static List<?> list2;
    static List<? extends Object> list3;

    static void assign(List list){
        list1 = list;
        list2 = list;
        list3 = list;
    }


    static void assign2(List<?> list){
        list1 = list;
        list2 = list;
        list3 = list;
    }

    static void assign3(List<? extends Object> list){
        list1 = list;
        list2 = list;
        list3 = list;
    }

    public static void main(String[] args) {
        List list = new ArrayList();
        assign(new ArrayList());
        assign2(new ArrayList());
        assign3(new ArrayList());

        List<String> stringList = new LinkedList<>();
        assign(stringList);
        assign2(stringList);
        assign3(stringList);
    }
}
