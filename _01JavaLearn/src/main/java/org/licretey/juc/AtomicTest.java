package org.licretey.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 雷智民
 * @version 1.0
 * @description: <对此类的描述>
 * @date 2024/5/14 11:23
 */
public class AtomicTest {
    public static void main(String[] args) {

        // 原子性测试
        AtomicInteger a = new AtomicInteger(10);
        int bf = a.getAndAdd(-3);
        System.out.println(bf);
        System.out.println(a.get());

    }
}
