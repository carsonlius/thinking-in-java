package com.carsonlius.thinkinjava.threads;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class TaskWithResult implements Callable<String> {
    private static int count;

    private final int id = count++;

    @Override
    public String call() throws Exception {
        TimeUnit.MILLISECONDS.sleep(1000);
        return "TaskWithResult id:" + id;
    }
}

public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        List<Future<String>> futureList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            futureList.add(executorService.submit(new TaskWithResult()));
        }

        System.out.println("hello world");
        futureList.forEach(item->{
            System.out.println(item.isDone());

            try {
                System.out.println(item.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
                executorService.shutdown();
            }
            System.out.println(item.isDone());
        });
        System.out.println("hello world ------>");

        System.out.println(executorService.isShutdown());


    }
}
