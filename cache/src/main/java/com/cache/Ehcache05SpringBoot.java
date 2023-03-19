package com.cache;

import com.cache.bean.User;
import com.cache.bean.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Ehcache05SpringBoot {
    /**
     * 使用springboot的缓存抽象的步骤
     * 1. 在application.yml中指定ehcache的配置文件
     * 2. @EnableCaching
     */

    @Resource
    private CacheManager cacheManager;
    @Resource
    private UserService userService;

    @Test
    public void test() {
        // org.springframework.cache.annotation.EnableCaching
        System.out.println(cacheManager.getClass());

        User cacheUser = userService.getById(3L);
        System.out.println("通过spring注解获取到的对象：" + cacheUser);
        System.out.println("2：" + cacheUser);
        System.out.println("3：" + cacheUser);
    }
}
