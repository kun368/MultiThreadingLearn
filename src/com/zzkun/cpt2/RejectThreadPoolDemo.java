package com.zzkun.cpt2;

import java.util.Arrays;
import java.util.concurrent.*;

/**
 * Created by zhangzhengkun on 2017/6/30.
 */
public class RejectThreadPoolDemo {

    public static class MyTask implements Runnable {

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + ": Thread id: " + Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyTask task = new MyTask();
        ExecutorService service = new ThreadPoolExecutor(5, 5, 0,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(10), Executors.defaultThreadFactory(), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println(r.toString() + " is rejected!");
            }
        });
        for (int i = 0; i < Integer.MAX_VALUE; ++i) {
            service.submit(task);
            Thread.sleep(10);
        }
    }
}
