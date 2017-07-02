package com.zzkun.cpt4;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Created by zhangzhengkun on 2017/7/2.
 */
public class AtomicIntegerArrayDemo {
    static AtomicIntegerArray arr = new AtomicIntegerArray(10);

    public static class AddThread implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 100000; ++i) {
                arr.incrementAndGet(i % arr.length());
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
        System.out.println(arr);
    }
}
