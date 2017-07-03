package com.zzkun.cpt5.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by zhangzhengkun on 2017/7/2.
 */
public class FutureMain {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask<String> future = new FutureTask<String>(new RealData("a"));
        ExecutorService service = Executors.newFixedThreadPool(1);
        service.submit(future);
        System.out.println("请求完毕");
        Thread.sleep(2000);
        System.out.println("数据=" + future.get());
    }
}
