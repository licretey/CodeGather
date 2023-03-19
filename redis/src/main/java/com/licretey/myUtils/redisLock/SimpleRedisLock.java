package com.licretey.myUtils.redisLock;

import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class SimpleRedisLock implements ILock{
    /**
     * 此版本问题：
     * 1. 不可重入：同一线程无法多次获取同一把锁
     * 2. 不可重试：获取锁时失败了直接返回了没有等待
     * 3. 业务太长会无法避免的超时释放，存在隐患
     * 4. redis若提供主从集群，主从间未同步某线程获取的锁，那个这个锁会被另外的线程在新主节点上直接获得
     */

    private String lockName; // 对应数据库中的某条数据
    private StringRedisTemplate stringRedisTemplate;
    public static final String KEY_PREFIX = "RedisLock";
    public static final String ID_PREFIX = UUID.randomUUID().toString().replaceAll("-","")+"-";

    public SimpleRedisLock(String lockName, StringRedisTemplate stringRedisTemplate) {
        this.lockName = lockName;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean tryLock(long timeoutSec) {
        // 获取当前线程id：用于删除时校验，防止释放错误的锁
        String threadId = ID_PREFIX + Thread.currentThread().getId();
        // 获取锁：并发线程持有的name被锁定，同一时间仅允许一个线程持有name对应的数据
        Boolean success = stringRedisTemplate.opsForValue()
                .setIfAbsent(KEY_PREFIX + lockName, threadId, timeoutSec, TimeUnit.SECONDS);
        return Boolean.TRUE.equals(success); // 注意自动拆箱
    }


    /**
     * 静态加载指定路径下的lua脚本，并初始化设置
     */
    private static final DefaultRedisScript<Long> UNLOCK_SCRIPT;
    static {
        UNLOCK_SCRIPT = new DefaultRedisScript<>();
        UNLOCK_SCRIPT.setLocation(new ClassPathResource("RedisUnLock.lua"));
        UNLOCK_SCRIPT.setResultType(Long.class);
    }

    /**
     * 基于lua脚本处理fullgc时释放锁的问题
     * （通过lua脚本保持原子性操作）
     */
    @Override
    public void unlock() {
        // 调用lua脚本执行
        stringRedisTemplate.execute(
                UNLOCK_SCRIPT,
                Collections.singletonList(KEY_PREFIX+lockName),
                ID_PREFIX + Thread.currentThread().getId()
        );
    }


    public void unlockV1() {
        String threadId = ID_PREFIX + Thread.currentThread().getId();
        String lockThread = stringRedisTemplate.opsForValue().get(KEY_PREFIX + lockName);
        // 校验防止释放错误锁
        if(threadId.equals(lockThread)){
            // 这中间，jvm在做fullgc时的阻塞仍然可能造成锁的超时释放，然后下方语句再执行释放了错误的锁
            stringRedisTemplate.delete(KEY_PREFIX+lockName);
        }
    }

}
