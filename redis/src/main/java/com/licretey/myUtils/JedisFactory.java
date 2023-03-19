package com.licretey.myUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisFactory {
    private static JedisPool jedisPool = null;

    {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(8); // 最大连接
        jedisPoolConfig.setMaxIdle(8); // 最大空闲连接
        jedisPoolConfig.setMinIdle(0); //最小空闲连接
        jedisPoolConfig.setMaxWaitMillis(100); //最长等待时间
        jedisPool = new JedisPool(jedisPoolConfig,"192.168.150.101",6379,1000,"123456");
    }

    //获取jedis连接对象
    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
}
