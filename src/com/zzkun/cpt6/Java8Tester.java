package com.zzkun.cpt6;

import java.util.Arrays;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

/**
 * Created by zhangzhengkun on 2017/7/4.
 */
public class Java8Tester {

    static int arr[] = {1, 3, 5, 6, 7, 8, 10};

    public static void main(String[] args) {
        Arrays.stream(arr).forEach(new IntConsumer() {
            @Override
            public void accept(int value) {
                System.out.println(value);
            }
        });
        long res = IntStream.range(0, 1111111111).parallel().filter((x) -> x % 2 == 1).count();
        System.out.println(res);
    }
}
