package com.carsonlius.thinkinjava.threads;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

class MyCatchHandle implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("------>  捕捉到异常 thread:" + t + " exception" + e);
    }
}

class HandleCatchExceptionThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread thread  = new Thread(r);
        thread.setUncaughtExceptionHandler(new MyCatchHandle());
        return thread;
    }
}
public class CaptureUncaughtException {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool(new HandleCatchExceptionThreadFactory());
        executorService.execute(new ExceptionThread());
    }

}
