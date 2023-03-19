package com.cache;

import com.cache.bean.User;
import com.cache.bean.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:cache/spring-ehcache.xml")
public class Ehcache04SpringCacheable {
    /**
     * 使用spring的缓存抽象的方式
     * 2. @Cacheable注解
     */
    @Resource
    private UserService userService;

    @Test
    public void test() {
        User cacheUser = userService.getById(3L);
        System.out.println("通过spring注解获取到的对象：" + cacheUser);
        System.out.println("2：" + cacheUser);
        System.out.println("3：" + cacheUser);
    }
}
