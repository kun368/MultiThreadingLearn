package com.zzkun.cpt4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhangzhengkun on 2017/7/2.
 */
public class AtomicIntegerDemo {

    static AtomicInteger i = new AtomicInteger();

    public static class AddThread implements Runnable {

        @Override
        public void run() {
            for (int k = 0; k < 100000; ++k) {
                i.incrementAndGet();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int k = 0; k < 10; ++k) {
            threads[k] = new Thread(new AddThread());
            threads[k].start();
        }
        for (int k = 0; k < 10; ++k) {
            threads[k].join();
        }
        System.out.println(i);
    }

}
