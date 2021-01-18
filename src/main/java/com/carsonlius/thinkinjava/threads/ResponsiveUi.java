package com.carsonlius.thinkinjava.threads;

import java.io.IOException;
import java.util.Arrays;

class UnResponsiveUi {
    private volatile double d =1;

    public UnResponsiveUi() throws IOException {
        run();
    }

    private void run() throws IOException {
        while (d > 0) {
            d += (Math.PI + Math.E)/d;
        }
        System.in.read();
    }
}

public class ResponsiveUi extends Thread {
    private static volatile  double d = 1;
    public ResponsiveUi() {
        setDaemon(true);
        start();
    }

    @Override
    public void run() {
        while (d > 0) {
            d += (Math.PI + Math.E)/d;
        }
    }

    public static void main(String[] args) throws IOException {
//        new UnResponsiveUi();
        new ResponsiveUi();
        byte[] buffer = new byte[1000];
        System.in.read(buffer);
        System.out.println(Arrays.toString(buffer));
        System.out.println("d :" + d);
    }
}
