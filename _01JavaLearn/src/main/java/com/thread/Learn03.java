package com.thread;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Learn03{
    // jdk自带线程安全类
    public static void main(String[] args) {
        Hashtable<String, String> table = new Hashtable<>();
        table.put("yang","11");

        ExecutorService pool = Executors.newCachedThreadPool();

        // 默认使用守护线程，所以优先使用自定义线程池
        CompletableFuture<Void> okkk = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("111");
        }, pool).whenComplete((r, e) -> {
            System.out.println("okkk");
        });


        System.out.println("over!!");
        pool.shutdown();
    }

    // 通过泛型实现通用快速排序

}


