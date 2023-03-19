package com.licretey.myUtils.redisLock;

public interface ILock {

    /**
     * 非阻塞模式的尝试获取锁
     *
     * @param timeoutSec 锁持有时间，超时后自动释放
     * @return true表示获取锁成功，false表示获取失败
     */
    boolean tryLock(long timeoutSec);

    /**
     * 释放锁
     */
    void unlock();
}
