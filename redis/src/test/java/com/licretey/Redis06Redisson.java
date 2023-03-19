package com.licretey;


import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


public class Redis06Redisson {

    @Resource
    private RedissonClient redissonClient;

    @Test
    public void testLock() throws InterruptedException {
        RLock lock = redissonClient.getLock("anyLock"); // 可重入锁：原理是记录了获取锁的次数
        // 参数：等待时间，锁自动释放时间，时间单位
        boolean gotLock = lock.tryLock(1, 10, TimeUnit.SECONDS);
        if(gotLock){
            try{
                System.out.println("执行业务");
            }finally {
                lock.unlock();
            }
        }
    }
}
