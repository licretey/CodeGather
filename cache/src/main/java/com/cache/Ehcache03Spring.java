package com.cache;


import com.cache.bean.User;
import net.sf.ehcache.Element;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:cache/spring-ehcache.xml")
public class Ehcache03Spring {

    /**
     * 使用spring的缓存抽象的方式
     * 1. 编程式操作spring的缓存抽象api
     */
    @Resource
    private CacheManager cacheManager; // spring cacheManager

    @Test
    public void test() {
        Cache userCache = cacheManager.getCache("user_cache");

        User user = new User();
        user.setId(1L);
        user.setName("licretey");
        userCache.put(user.getId(), user);
        /**
         * 获取cache中的对象
         * 由于配置的是写入到磁盘，这里未序列化，故spring会进行提示
         *  1. 可以将策略配置为None方式
         *  2. 可让User对象继承序列化接口
         */
        User getUser = userCache.get(1L, User.class);
        System.out.println("获取到的对象：" + getUser);
    }

}
