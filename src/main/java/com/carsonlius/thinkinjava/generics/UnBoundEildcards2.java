package com.carsonlius.thinkinjava.generics;

import java.util.HashMap;
import java.util.Map;

public class UnBoundEildcards2 {
    static Map map;
    static Map<?,?> map2;
    static Map<String, ?> map3;
    static void assign(Map map){
        UnBoundEildcards2.map = map;
    }

    static void assign2(Map<?, ?> map){
        map2 = map;
    }

    static void assign3(Map<String, ?> map){
        map3 = map;
    }

    public static void main(String[] args) {
        Map map = new HashMap();
        assign(map);
        assign2(map);
        assign3(map);
    }
}
