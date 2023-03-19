package com.cache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 基于LinkeList实现LRU算法的自定义Cache
 */
public class Ehcache09SelfCacheV2 implements Ehcache09SelfCache {
    // 缓存容量
    private int capacity;
    // 通过组合持有一个内部的真正缓存对象
    private Map<Object, Object> internalCache;
    // 用来维护缓存key的顺序
    private LinkedList<Object> keyList;

    public Ehcache09SelfCacheV2(int capacity) {
        this.capacity = capacity;
        this.internalCache = new HashMap<>();
        this.keyList = new LinkedList<>();
    }

    @Override
    public void put(Object key, Object value) {
        // put时容量已满，先移除一个再放入
        if (this.size() == this.capacity) {
            // 移除第一个即是最老的，因为get时对linkedlist排序了
            Object firstKey = keyList.removeFirst();
            this.internalCache.remove(firstKey);
        }
        this.keyList.addLast(key);
        this.internalCache.put(key, value);
    }

    @Override
    public void remove(Object key) {
        this.keyList.remove(key);
        this.internalCache.remove(key);
    }

    @Override
    public void clear() {
        this.keyList.clear();
        this.internalCache.clear();
    }

    @Override
    public Object get(Object key) {
        // 存在时为true, 不存在为false
        boolean removeResult = this.keyList.remove(key);
        if (removeResult) {
            Object value = internalCache.get(key);
            // 把现在访问的key放到linkedList的最后
            this.keyList.addLast(key);
            return value;
        }
        return null; // 不存在时返回null
    }

    @Override
    public int size() {
        // return this.keyList.size();
        return this.internalCache.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Object key : keyList) {
            sb.append("key:").append(key).append(",")
                    .append("value").append(internalCache.get(key))
                    .append("-");
        }
        return "Ehcache09SelfCacheV2{" +
                "capacity=" + capacity +
                "nowSize=" + this.size() +
                ", internalCache=" + sb.toString();
    }
}
