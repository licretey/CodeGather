package com.licretey;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import javax.management.ObjectName;
import java.util.Map;

public class JedisDemo01 {

    private Jedis jedis;


    @BeforeEach
    void setUp(){
        jedis = new Jedis("localhost",6379);  // 建立连接
        jedis.auth("123456");// 填写密码
        jedis.select(0); // 选择库
    }


    @Test
    void testStr(){
        String rs = jedis.set("username", "googe");
        String username = jedis.get("username");
    }

    @Test
    void testHash(){
        Long hset = jedis.hset("username", "field1", "googe");
        String username = jedis.get("username");

        String goo = jedis.hget("username", "feild1");
        Map<String, String> user = jedis.hgetAll("username");
    }

    @AfterAll
    void turnDown(){
        if(jedis != null){
            jedis.close();
        }
    }
}
