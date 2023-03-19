package com.cache;

import com.cache.bean.Dept;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

public class Ehcache09SelfCacheTest {
    /**
     * 功能可能涉及：
     * 最大多少个
     * 超过最大个数时的淘汰策略：LRU, LFU, FIFO
     * 过期需要清除
     * 内存敏感（不要因为缓存导致业务程序的OOM）
     * 线程的安全
     * 统计命中率
     * 序列化扩展
     */


    /**
     * LRU: 最近最少使用
     * 要实现基于LRU算法的溢出驱逐：
     * 1. 按访问时间来排序
     * 2. 移除排在最前面的
     */
    @Test
    public void testV1LinkedHashMapCache() {
        Ehcache09SelfCache cache = new Ehcache09SelfCacheV1(3);
        cache.put("a", "a_value");
        cache.put("b", "b_value");
        cache.put("c", "c_value");
        System.out.println(cache);
        // 访问一下元素，看是否改变了排序
        String bValue = (String) cache.get("b");
        System.out.println(cache);
        // 测试是否会移除
        cache.put("d", "d_value");
        System.out.println(cache);
    }

    @Test
    public void testV2LinkedListCache() {
        Ehcache09SelfCache cache = new Ehcache09SelfCacheV2(3);
        cache.put("a", "a_value");
        cache.put("b", "b_value");
        cache.put("c", "c_value");
        System.out.println(cache);
        // 访问一下元素，看是否改变了排序
        String bValue = (String) cache.get("b");
        System.out.println(cache);
        // 测试是否会移除
        cache.put("d", "d_value");
        System.out.println(cache);
    }


    /**
     * 测试强应用OOM
     * 启动时在项目设置里最好设置下jvm的堆大下，如：-Xms20M -Xmx20M -XX:+PrintGCDetails
     */
    @Test
    public void testV3LinkedHashMapCache() {
        Ehcache09SelfCache cache = new Ehcache09SelfCacheV1(1000);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            Dept dept = new Dept((long) i);
            System.out.println("放入第"+i+"个");
            cache.put(dept.getId(), dept);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 测试软应用在内存敏感的cache中
     * 启动时在项目设置里最好设置下jvm的堆大下，如：-Xms20M -Xmx20M -XX:+PrintGCDetails
     */
    @Test
    public void testV3LinkedHashMapCache2() {
        // Ehcache09SelfCache cache = new Ehcache09SelfCacheV3(1000);
        // for (int i = 0; i < Integer.MAX_VALUE; i++) {
        //     Dept dept = new Dept((long) i);
        //     System.out.println("放入第"+i+"个");
        //     cache.put(dept.getId(), dept);
        //     try {
        //         TimeUnit.SECONDS.sleep(1);
        //     } catch (InterruptedException e) {
        //         e.printStackTrace();
        //     }
        // }
    }

    // lindedHashMap可以实现LRU的按时间来排序
    private void LRUByLinkedHashMap() {
        // accessOrder: true时会按时间排序
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(16, 0.75f, true);
        linkedHashMap.put("a", "a_value");
        linkedHashMap.put("b", "b_value");
        linkedHashMap.put("c", "c_value");
        System.out.println(linkedHashMap);
        String bValue = linkedHashMap.get("b");
        System.out.println(linkedHashMap);
    }
}
