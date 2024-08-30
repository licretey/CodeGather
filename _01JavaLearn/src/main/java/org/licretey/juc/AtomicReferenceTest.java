package org.licretey.juc;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * 
 * @version 1.0
 * @description: 原子引用
 * @date 2024/5/14 11:23
 */
public class AtomicReferenceTest {
    public static void main(String[] args) {

        // 原子性测试
        AtomicInteger a = new AtomicInteger(10);
        int bf = a.getAndAdd(-3);
        System.out.println(bf);
        System.out.println(a.get());
        LongAdder la = new LongAdder();
        // Collections.synchronizedMap()

    }
}
