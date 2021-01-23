package com.carsonlius.thinkinjava.threads;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CloseResource {
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(8080);
        InputStream stream = new Socket("localhost", 8080).getInputStream();
//        executorService.execute(new IoBlocked(stream));
        executorService.execute(new IoBlocked(System.in));



    }
}
