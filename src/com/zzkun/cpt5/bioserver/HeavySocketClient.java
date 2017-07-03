package com.zzkun.cpt5.bioserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by zhangzhengkun on 2017/7/3.
 */
public class HeavySocketClient {

    private static final int SLEEP_TIME = 1024 * 1024 * 1024;

    public static class MyClient implements Runnable {

        @Override
        public void run() {
            Socket clientSocket = null;
            PrintWriter out = null;
            BufferedReader in = null;
            try {
                clientSocket = new Socket();
                clientSocket.connect(new InetSocketAddress("localhost", 8080));
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                out.print("H");
                LockSupport.parkNanos(SLEEP_TIME);
                out.print("E");
                LockSupport.parkNanos(SLEEP_TIME);
                out.print("L");
                LockSupport.parkNanos(SLEEP_TIME);
                out.print("L");
                LockSupport.parkNanos(SLEEP_TIME);
                out.print("O");
                LockSupport.parkNanos(SLEEP_TIME);
                out.print("!");
                LockSupport.parkNanos(SLEEP_TIME);
                out.println();
                out.flush();

                System.out.println("from server: " + in.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (out != null)
                        out.close();
                    if (in != null)
                        in.close();
                    if (clientSocket != null)
                        clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; ++i) {
            new Thread(new MyClient()).start();
        }
    }

}
