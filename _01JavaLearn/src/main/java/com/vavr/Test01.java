package com.vavr;

import io.vavr.Function1;
import io.vavr.Function4;

/**
 * @Description:
 * @Date: 2024/5/29 14:05
 */

import io.vavr.Function1;
import io.vavr.Function4;

import java.util.concurrent.CompletableFuture;

public class Test01 {

    public static void main(String[] args) {
        Function4<Integer, Integer, Integer, Integer, Integer> sum = (a, b, c, d)->{
            return a+b+c+d;
        };

        Function1<Integer, Integer> sum2 = sum.apply(2,2,2);
        System.out.println(sum2.apply(10));

        // CompletableFuture.
    }
}


