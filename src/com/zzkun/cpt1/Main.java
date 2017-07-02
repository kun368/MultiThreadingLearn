package com.zzkun.cpt1;

public class Main {

    static final Integer t = 1;

    public static void main(String[] args) throws InterruptedException {

        synchronized (t) {
            System.out.println("111111");
            t.wait();
            System.out.println("2222222");
        }

    }
}
