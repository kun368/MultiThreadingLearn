package com.zzkun.cpt5.pipeline;

/**
 * Created by zhangzhengkun on 2017/7/2.
 */
public class PStreamMain {
    public static void main(String[] args) {
        new Thread(new Plus()).start();
        new Thread(new Multiply()).start();
        new Thread(new Div()).start();
        for (int i = 1; i <= 1000; ++i) {
            for (int j = 0; j <= 1000; ++j) {
                Msg msg = new Msg();
                msg.i = i;
                msg.j = j;
                msg.orgStr = String.format("((%d+%d)*%d)/2", i, j, i);
                Plus.bq.add(msg);
            }
        }
    }
}
