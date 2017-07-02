package com.zzkun.cpt2;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhangzhengkun on 2017/6/29.
 */
public class ReenterLockTest implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();

    public static int i = 0;


    @Override
    public void run() {
        for (int j = 0; j < 1000000; ++j) {
            lock.lock();
            i++;
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new ReenterLockTest());
        Thread t2 = new Thread(new ReenterLockTest());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(ReenterLockTest.i);
    }
}
