package org.licretey;

import com.alibaba.druid.util.LRUCache;

import java.io.UnsupportedEncodingException;

public class LRUMapTest {
    private static final LRUCache<String, String> cache = new LRUCache<>(2);

    public static void main(String[] args) throws UnsupportedEncodingException {

        cache.computeIfAbsent("yang",n-> n);
        cache.computeIfAbsent("jian",n-> n);
        cache.forEach((cc,cd) ->{
            System.out.println(cc+" "+cd);
        });
        System.out.println("************************");
        cache.computeIfAbsent("ping",n-> n);
        cache.forEach((cc,cd) ->{
            System.out.println(cc+" "+cd);
        });
        System.out.println("************************");
        cache.computeIfAbsent("jian",n-> n);
        cache.forEach((cc,cd) ->{
            System.out.println(cc+" "+cd);
        });
        System.out.println("************************");

        cache.computeIfAbsent("jian2",n-> n);
        cache.forEach((cc,cd) ->{
            System.out.println(cc+" "+cd);
        });
        System.out.println("************************");

    }


}
