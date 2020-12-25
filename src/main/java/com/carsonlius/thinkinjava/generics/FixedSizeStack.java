package com.carsonlius.thinkinjava.generics;

public class FixedSizeStack<T> {
    private int index = 0;
    private Object[] storage;

    public FixedSizeStack(int size) {
        storage = new Object[size];
    }

    public void push(T item){
        storage[index++] = item;
    }

    @SuppressWarnings("unchecked")
    public T pop(){
        return (T)storage[--index];
    }
}
