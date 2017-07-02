package com.zzkun.cpt2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by zhangzhengkun on 2017/6/30.
 */
public class CountTask extends RecursiveTask<Long> {

    private static final int THRESHOLD = 10000;
    private long start;
    private long end;

    public CountTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        if (end - start + 1 <= THRESHOLD) {
            for (long i = start; i <= end; ++i) {
                sum += i;
            }
        }
        else {
            List<CountTask> sub = new ArrayList<>();
            long l = start;
            long step = (end - start + 1) / 100;
            while (l <= end) {
                long r = l + step - 1;
                if (r > end) {
                    r = end;
                }
                CountTask cur = new CountTask(l, r);
                sub.add(cur);
                cur.fork();
                l += step;
            }
            for (CountTask task : sub) {
                sum += task.join();
            }
        }
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        CountTask task = new CountTask(0, 200000L);
        ForkJoinTask<Long> submit = pool.submit(task);
        System.out.println("sum = " + submit.get());
    }
}
