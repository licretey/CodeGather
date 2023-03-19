package com.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import java.util.concurrent.Callable;

/**
 * 自定义cache，实现了spring的cache接口，所以可以被spring认可
 * 自定义cache场景：
 * 1. 集成Guavacache
 * 2. 多级缓存
 */
public class Ehcache07GuavaSelfCache implements Cache {
    private String cacheName;
    /**
     * 使用组合模式持有真正的cache对象
     */
    private com.google.common.cache.Cache<Object, Object> internalCache;

    public Ehcache07GuavaSelfCache(String cacheName, com.google.common.cache.Cache<Object, Object> internalCache) {
        this.cacheName = cacheName;
        this.internalCache = internalCache;
    }

    @Override
    public String getName() {
        return cacheName;
    }

    @Override
    public Object getNativeCache() {
        return internalCache;
    }

    @Override
    public ValueWrapper get(Object key) {
        Object object = this.internalCache.getIfPresent(key);
        if (object != null) {
            // 返回valueWrapper的默认实现
            return new SimpleValueWrapper(object);
        }
        return null;
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        throw new RuntimeException("未实现，参考get实现");
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        throw new RuntimeException("未实现，参考get实现");
    }

    @Override
    public void put(Object key, Object value) {
        internalCache.put(key, value);

    }

    @Override
    public void evict(Object key) {
        internalCache.invalidate(key);
        // 若是多级缓存场景：这里就需要完成本地缓存和分布式缓存的同步逻辑
    }

    @Override
    public void clear() {
        internalCache.invalidateAll();
    }
}
