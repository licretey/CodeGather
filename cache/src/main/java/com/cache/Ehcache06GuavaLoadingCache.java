package com.cache;

import com.cache.bean.User;
import com.google.common.cache.*;

import java.util.concurrent.TimeUnit;

public class Ehcache06GuavaLoadingCache {
    /**
     * 单独使用gauva包中的cache，常用有2种：
     *      1. 实现自cache接口的com.google.common.cache.LocalCache.LocalLoadingCache
     *          特点：缓存种获取不到值，会根据指定的loader自动进行加载，加载后放入缓存
     *      2. 实现自cache接口的com.google.common.cache.LocalCache.LocalManualCache
     *          普通cache，类似ehcache
     */
    public static void main(String[] args) {
        LoadingCache<Long, User> loadingCache = CacheBuilder.newBuilder()
                // 设置并发级别
                .concurrencyLevel(8)
                // 初始化大小，配合concurrencyLevel做分段锁
                .initialCapacity(60)
                // 缓存种最多可放多少个元素
                .maximumSize(10)
                // 从写入开始计算，10s过期
                .expireAfterWrite(10, TimeUnit.SECONDS)
                // 统计命中率
                .recordStats()
                // 缓存中的元素被驱逐出去后会自动回调此处方法
                .removalListener(new RemovalListener<Long, User>() {
                    @Override
                    public void onRemoval(RemovalNotification<Long, User> removalNotification) {
                        Long key = removalNotification.getKey();
                        RemovalCause cause = removalNotification.getCause();
                        System.out.println("key"+key+" 被移出缓存，原因："+cause);
                        return;
                    }
                })
                // 缓存种取不到值，会回调到这里
                .build(new CacheLoader<Long, User>() {
                    // key: 将来通过loadingCache.get(k)未从缓存种获取到对象时的key
                    @Override
                    public User load(Long key) throws Exception {
                        // 可以加载数据
                        System.out.println("模拟去存储系统种加载了数据");
                        User user = new User();
                        user.setId(key);
                        user.setName("licretey3, id "+key);
                        return user;
                    }
                });
        for (int i = 0; i < 20; i++) {
            // 需要抛异常
            // User user = loadingCache.get(2L);
            User user = loadingCache.getUnchecked(22L);
            System.out.println(user);
        }
        // 统计信息
        System.out.println(loadingCache.stats().toString());
    }

}
