package com.thread;

import java.util.Hashtable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AutomicTest {
    // jdk自带线程安全类
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        // 返回操作前的
        int andIncrement = atomicInteger.getAndIncrement();
        // 返回操作后的
        int i = atomicInteger.incrementAndGet();
    }

}
