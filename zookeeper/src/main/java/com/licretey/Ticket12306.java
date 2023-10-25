package com.licretey;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

public class Ticket12306 implements Runnable{
    private int tickets = 10;
    private InterProcessMutex lock;

    public Ticket12306(){
        RetryPolicy rt = new ExponentialBackoffRetry(3000, 10);
        CuratorFramework curator = CuratorFrameworkFactory.builder()
                .connectString("")
                .sessionTimeoutMs(60 * 1000)
                .sessionTimeoutMs(15 * 1000)
                .retryPolicy(rt)
                // 不必要，设置命名空间
                .namespace("licretey")
                .build();
        curator.start();

        lock = new InterProcessMutex(curator,"/lock01");
    }

    @Override
    public void run() {
        while (true){
            try {
                lock.acquire(3, TimeUnit.SECONDS);
                if(tickets>0){
                    System.out.println(Thread.currentThread()+" : "+tickets);
                    tickets--;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    lock.release();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

        }

    }
}
