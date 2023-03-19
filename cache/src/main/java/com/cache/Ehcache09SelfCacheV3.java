//
// package com.cache;
//
// import java.lang.ref.SoftReference;
// import java.util.LinkedHashMap;
// import java.util.Map;
//
// /**
//  * 基于LinkedHashMap实现LRU算法的自定义Cache
//  * 增强：基于SoftReference实现缓存的内存敏感能力
//  * 引用类型：
//  * 强引用：只要有引用就不会被回收，基本OOM
//  * 弱引用：每次gc都会被回收
//  * 软引用：gc且堆内存吃紧的时候才会被回收
//  * 虚引用：随时都有可能被回收
//  */
//
//
// // java使用了未经检查或不安全的操作
// public class Ehcache09SelfCacheV3 implements Ehcache09SelfCache {
//     // 缓存容量
//     private int capacity;
//     // 通过组合持有一个内部的真正缓存对象
//     private InternalCache internalCache;
//
//     private static class InternalCache extends LinkedHashMap<Object, Object> {
//         private int capacity;
//
//         public InternalCache(int capacity) {
//             // 开启按时间排序
//             super(16, 0.75f, true);
//             capacity = capacity;
//         }
//
//         /**
//          * 返回true时清除最前面的元素（最老的），false不清除
//          * LinkedHashMap在put完数据后，会回调下方方法，会根据该方法的结果作为条件之一，判断是否清除首元素
//          *
//          * @param eldest
//          * @return
//          */
//         @Override
//         protected boolean removeEldestEntry(Map.Entry<Object, Object> eldest) {
//             return size() > capacity;
//         }
//     }
//
//     public Ehcache09SelfCacheV3(int capacity) {
//         this.capacity = capacity;
//         internalCache = new InternalCache(this.capacity);
//     }
//
//     @Override
//     public void put(Object key, Object value) {
//         SoftReference<Object> softReference = new SoftReference<>(value);
//         this.internalCache.put(key, softReference);
//     }
//
//     @Override
//     public void remove(Object key) {
//         this.internalCache.remove(key);
//     }
//
//     @Override
//     public void clear() {
//         this.internalCache.clear();
//     }
//
//     @Override
//     public Object get(Object key) {
//         Object o = this.internalCache.get(key);
//         if (o == null) {
//             return null;
//         }
//         SoftReference<Object> softReference = (SoftReference<Object>) o;
//         // 返回引用的真实对象
//         return softReference.get();
//     }
//
//     @Override
//     public int size() {
//         return this.internalCache.size();
//     }
//
//     @Override
//     public String toString() {
//         return "Ehcache09SelfCacheV1{" +
//                 "capacity=" + capacity +
//                 ", internalCache=" + internalCache +
//                 '}';
//     }
// }