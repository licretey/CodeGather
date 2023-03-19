package com.licretey;


import com.licretey.dto.Order;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.aop.framework.AopContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class Redis08RedisMQ {

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @Test
    public String testSecKill() throws InterruptedException {
        Long userId = 112233L;
        String orderId = UUID.randomUUID().toString().replaceAll("-", "");


        return "下单成功";
    }



    @Test
    public void testAnd(){
        long a = 5;
        System.out.println("aaa: "+  (5 & 1));
    }

}
