package com.licretey;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
public class Redis03Demo {
    @Resource
    private RedisTemplate redisTemplate;

    @Test
    void testString (){
        redisTemplate.opsForValue().set("username","lisi");
        Object username = redisTemplate.opsForValue().get("username");
        /**
         * SpringBootDataRedis默认会将传入的数据序列化为字节存入到库中，若不想如此需要设置序列化方式
         *
         * StringRedisSerializer
         * GenericJackson2JsonRedisSerializer
         */
        //

    }
}
