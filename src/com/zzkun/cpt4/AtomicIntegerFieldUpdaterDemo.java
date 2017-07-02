package com.zzkun.cpt4;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Created by zhangzhengkun on 2017/7/2.
 */
public class AtomicIntegerFieldUpdaterDemo {
    public static class Candidate {
        int id;
        volatile int score;
    }
    public final static AtomicIntegerFieldUpdater<Candidate> scoreUpdat
            = AtomicIntegerFieldUpdater.newUpdater(Candidate.class, "score");

    public static AtomicInteger allScore = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        final Candidate stu = new Candidate();
        Thread[] ts = new Thread[10000];
        for (int i = 0; i < 10000; ++i) {
            ts[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    if (Math.random() > 0.4) {
                        scoreUpdat.incrementAndGet(stu);
                        allScore.incrementAndGet();
                    }
                }
            });
            ts[i].start();
        }
        for (int i = 0; i < ts.length; ++i) {
            ts[i].join();
        }
        System.out.println("score = " + scoreUpdat.get(stu));
        System.out.println("allScore = " + allScore.get());
    }
}
