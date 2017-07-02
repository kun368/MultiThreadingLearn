package com.zzkun.cpt2;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by zhangzhengkun on 2017/6/29.
 */
public class CyclicBarrierDemo {
    public static class Solider implements Runnable {

        private String soldier;
        private final CyclicBarrier barrier;

        public Solider(String soldier, CyclicBarrier barrier) {
            this.soldier = soldier;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                barrier.await();
                Thread.sleep(new Random().nextInt(10000));
                System.out.println(soldier + ": complete!");
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static class BarrierRun implements Runnable {

        boolean flag;
        int N;

        public BarrierRun(boolean flag, int n) {
            this.flag = flag;
            N = n;
        }

        @Override
        public void run() {
            if (flag) {
                System.out.println("司令：[士兵" + N + "个，任务完成！]");
            } else {
                System.out.println("司令：[士兵" + N + "个，集合完毕！]");
                flag = true;
            }
        }
    }

    public static void main(String[] args) {
        final int N = 10;
        Thread[] allSoldier = new Thread[N];
        boolean flag = false;
        CyclicBarrier barrier = new CyclicBarrier(N, new BarrierRun(flag, N));
        System.out.println("集合队伍~");
        for (int i = 0; i < N; ++i) {
            System.out.println("士兵 " + i + "报到！");
            allSoldier[i] = new Thread(new Solider("士兵 " + i, barrier));
            allSoldier[i].start();
        }
    }
}
