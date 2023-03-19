
package com.cache;

import com.cache.bean.User;
import com.cache.bean.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Arrays;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:cache/guava/spring-guava-cache-generator.xml"})
public class Ehcache08KeyGenerator {

    @Resource
    private CacheManager cacheManager;
    @Resource
    private UserService userService;

    /**
     * 测试自定义的keyGenerator
     */
    @Test
    public void test() {
        User queryParam = new User();
        queryParam.setId(666L);
        queryParam.setName("xiaojun");
        int[] arr = new int[2];
        Arrays.fill(arr,1);
        User user = userService.getUser(queryParam, arr,"abc");
        System.out.println(user);
        System.out.println(userService.getUser(queryParam,arr,"abc"));
        System.out.println(userService.getUser(queryParam,arr,"abc"));
    }
}
