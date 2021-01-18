package com.carsonlius.thinkinjava.threads;

public class SerialNumberGenerator {
    private static volatile int currentValue;

    public synchronized static int nextSerialValue() {
       return ++ currentValue;
    }
}
