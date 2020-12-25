package com.carsonlius.thinkinjava.generics;

import com.sun.deploy.security.BadCertificateDialog;

import java.util.ArrayList;
import java.util.List;

public class SuperTypeWildCards {
    static void writeTo(List<? super Apple> apples){
        apples.add(new Apple());
        apples.add(new JApple());
        System.out.println(apples);;
    }

    static void extendsWriteTo(List<? extends Apple> apples){

        System.out.println("不能添加具体的数据，因为此时不知道apples代表的含义，可能他是上限" + apples.getClass().getCanonicalName());;
    }

    public static void main(String[] args) {
//       f1();
       writeTo(appleList);
         List<Fruit> fruits = new ArrayList<Fruit>();
        writeTo(fruits);

        extendsWriteTo(appleList);
    }

    static<T> void writeExact(List<T> list,T item){
        list.add(item);
        System.out.println(list);
    }

    static List<Apple> appleList = new ArrayList<Apple>();
    static List<Fruit> fruitList = new ArrayList<Fruit>();
    static void f1(){
        writeExact(appleList, new Apple());
        writeExact(fruitList, new Apple());
    }
}
