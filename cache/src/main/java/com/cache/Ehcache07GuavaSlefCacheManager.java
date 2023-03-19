package com.cache;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractCacheManager;

import java.util.Collection;
import java.util.LinkedHashSet;

// spring-guava-cache.xml中配置的cacheManager在spring中可能未实现，所以需要自定义
public class Ehcache07GuavaSlefCacheManager extends AbstractCacheManager {

    /**
     * 用来加载当前cacheManager要管理哪些cahche
     *
     * @return
     */
    @Override
    protected Collection<? extends Cache> loadCaches() {
        com.google.common.cache.Cache<Object, Object> userCache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .build();
        com.google.common.cache.Cache<Object, Object> bookCache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .build();
        Ehcache07GuavaSelfCache selfUserCache = new Ehcache07GuavaSelfCache("user_cache", userCache);
        Ehcache07GuavaSelfCache selfBookCache = new Ehcache07GuavaSelfCache("book_cache", bookCache);
        Collection<Cache> caches = new LinkedHashSet<>();
        caches.add(selfUserCache);
        caches.add(selfBookCache);
        return caches;
    }
}
