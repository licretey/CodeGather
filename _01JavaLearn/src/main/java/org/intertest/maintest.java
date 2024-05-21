package org.intertest;

import com.alibaba.druid.util.LRUCache;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class maintest {

    public static void main(String[] args) {
        List<Itest> items = new ArrayList<>();
        ItestD1Demo t1 = new ItestD1Demo();
        t1.setName("yang");
        t1.setUsed(false);
        t1.setAge(23L);
        LRUCache<String,Itest> cache = new LRUCache<>(100);

        // cache.getAndPut("yang", new ItestD2Demo());
        ItestD2Demo t2 = new ItestD2Demo();
        t2.setAddr("aaa");
        t2.setName("jian");
        t2.setAge(22L);
        cache.put("yang",t1);
        cache.put("jian",t2);
        Itest yang = cache.computeIfAbsent("jian", str -> {
            return new ItestD2Demo();
        });

        System.out.println(yang.toString());
        System.out.println(cache.size());

        items.forEach(item ->{
            System.out.println(item);
        });

        for (int i = 0; i < items.size(); i++) {

        }

    }
}
