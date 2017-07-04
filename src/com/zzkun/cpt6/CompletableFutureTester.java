package com.zzkun.cpt6;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by zhangzhengkun on 2017/7/4.
 */
public class CompletableFutureTester {

    public static class AskThread implements Runnable {

        CompletableFuture<Integer> cf;

        public AskThread(CompletableFuture<Integer> cf) {
            this.cf = cf;
        }

        @Override
        public void run() {
            int myRe = 0;
            try {
                myRe = cf.get() * cf.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println(myRe);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final CompletableFuture<Integer> cf = new CompletableFuture<>();
        new Thread(new AskThread(cf)).start();
        Thread.sleep(1000);
        cf.complete(60);
    }

}
