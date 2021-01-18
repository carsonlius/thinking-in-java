package com.carsonlius.thinkinjava.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenChecker implements Runnable {
    private final int id ;

    private IntGenerator intGenerator;

    public EvenChecker(IntGenerator intGenerator, int id) {
        this.id = id;
        this.intGenerator = intGenerator;
    }

    /**
     * 测试是否是否生成的都是偶数
     * */
    @Override
    public void run() {
        while (!intGenerator.isCanceled()) {
            int next = intGenerator.next();
            if (next%2 != 0) {
                System.out.println("next val:" + next + " is not even" + " thread :" + Thread.currentThread());
                intGenerator.cancel();
            }
        }

        System.out.println("线程终结了");
    }

    public static void test(IntGenerator intGenerator, int count){
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i <count ; i++) {
            executorService.execute(new EvenChecker(intGenerator, i));
        }
        executorService.shutdown();
    }

    public static void test(IntGenerator intGenerator) {
        test(intGenerator, 10);
    }
}
