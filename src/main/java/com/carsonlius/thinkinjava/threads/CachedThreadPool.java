package com.carsonlius.thinkinjava.threads;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class CachedThreadPool {
    public static void main(String[] args) {

    }

    @GetMapping(value = "/testThread")
    public Object test(){

        ExecutorService service = Executors.newCachedThreadPool();


        for (int i = 0; i <5 ; i++) {
            service.execute(new LiftOff());
        }

        service.shutdown();
        System.out.println("hello world! ending");
        return new String("线程执行结束");
    }



}
