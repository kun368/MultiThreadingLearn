package com.zzkun.cpt5.bioserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by zhangzhengkun on 2017/7/3.
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket();
        clientSocket.connect(new InetSocketAddress("localhost", 8080));
        try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            out.println("Hello!");
            System.out.println("from server: " + in.readLine());
        }
    }
}
