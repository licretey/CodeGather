package com.thread;

import java.util.Hashtable;

public class Learn03{
    // jdk自带线程安全类
    public static void main(String[] args) {
        Hashtable<String, String> table = new Hashtable<>();
        table.put("yang","11");

        Thread thread = new Thread(() -> {
            System.out.println("111");
        });

        thread.start();
    }

    // 通过泛型实现通用快速排序

}
