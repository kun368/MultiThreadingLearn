package com.zzkun.cpt5.bioserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhangzhengkun on 2017/7/3.
 */
public class MultiThreadEchoServer {

    private static ExecutorService es = Executors.newCachedThreadPool();

    private static class HandleMsg implements Runnable {

        Socket clientSocket;

        public HandleMsg(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
                String line;
                while ((line = in.readLine()) != null) {
                    out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try (ServerSocket echoServer = new ServerSocket(8080)) {
            while (true) {
                Socket clientSocket = echoServer.accept();
                System.out.println(clientSocket.getRemoteSocketAddress() + " connect!");
                es.submit(new HandleMsg(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
