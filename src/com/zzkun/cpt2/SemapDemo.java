package com.zzkun.cpt2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by zhangzhengkun on 2017/6/29.
 */
public class SemapDemo implements Runnable {

    final Semaphore semp = new Semaphore(5);

    @Override
    public void run() {
        try {
            semp.acquire();
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getId() + ": done!");
            semp.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(20);
        final SemapDemo demo = new SemapDemo();
        for (int i = 0; i < 20; ++i) {
            service.submit(demo);
        }
        service.shutdown();
    }
}
