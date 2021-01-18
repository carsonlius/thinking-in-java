package com.carsonlius.thinkinjava.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NaiveExceptionHandling {
    public static void main(String[] args) {
        try {
            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.execute(new ExceptionThread());

        }catch (RuntimeException e) {
            System.out.println("捕捉到异常"+ e);
        }
    }
}
