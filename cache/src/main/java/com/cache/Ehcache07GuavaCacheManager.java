package com.cache;

import com.cache.bean.User;
import com.cache.bean.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:cache/guava/spring-guava-cache.xml"})
public class Ehcache07GuavaCacheManager {
    /**
     * 自定义cacheManager步骤
     *      1. 编写com.cache.Ehcache07GuavaSlefCacheManager
     *      2. 编写com.cache.Ehcache07GuavaSlefCache
     *      3. 配置spring-guava-cache.xml
     *      4. 测试使用
     *
     * spring中集成任何第三方框架：xml 或 @Configuration中@Bean注入
     * springBoot中集成任何第三方框架：starter 或 @Configuration中@Bean注入
     * @param args
     */
    @Resource
    private CacheManager cacheManager;
    @Resource
    private UserService userService;

    // spring-guava-cache.xml中配置的cacheManager在spring中可能未实现，所以需要自定义
    @Test
    public void test() {
        // 拿到com.cache.Ehcache07GuavaSlefCacheManager就可以使用了
        System.out.println(cacheManager.getClass());

        User cacheUser = userService.getById(3L);
        System.out.println("通过spring注解获取到的对象：" + cacheUser);
        System.out.println("2：" + cacheUser);
        System.out.println("3：" + cacheUser);
    }
}
