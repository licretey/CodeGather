package com.cache;

import com.cache.bean.User;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.util.Arrays;

public class Ehcache01Api {
    public static void main(String[] args) {
        String absPath = "D:\\code\\JavaStudy\\src\\main\\resources\\cache\\ehcache.xml";
        // 用来管理多个cache
        CacheManager cacheManager = CacheManager.create(absPath);
        // 获取到cacheManger管理的所有的cache
        String[] cacheNames = cacheManager.getCacheNames();
        System.out.println("cacheManger管理的所有的cache的名字：" + Arrays.toString(cacheNames));
        // 根据自定义的名称从manager中获取cache
        Cache userCache = cacheManager.getCache("user_cache");
        userCache.put(getElement());// cache中放入element对象
        // 通过element的id从cache中获取对象
        Element element = userCache.get(1L);
        System.out.println("获取到的Element返回对象：" + element);
        System.out.println("获取到的Element返回对象中的value：" + element.getObjectValue());
    }

    private static Element getElement() {
        User user = new User();
        user.setId(1L);
        user.setName("licretey");
        Element element = new Element(user.getId(), user);
        return element;
    }
}
