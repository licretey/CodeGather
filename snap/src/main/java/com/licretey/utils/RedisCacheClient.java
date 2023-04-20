package com.licretey.utils;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Slf4j
@Component
public class RedisCacheClient {
    private static StringRedisTemplate stringRedisTemplate;

    public RedisCacheClient(StringRedisTemplate redisTemplate) {
        this.stringRedisTemplate = redisTemplate;
    }

    public void set(String key, Object value, Long time, TimeUnit unit) {
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(value), time, unit);
    }

    public void setWithLogicExpire(String key, Object value, Long time, TimeUnit unit) {
        RedisData redisData = new RedisData();
        redisData.setData(value);
        redisData.setExpireTime(LocalDateTime.now().plusSeconds(unit.toSeconds(time)));
        // 逻辑过期本质永久有效
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(redisData));
    }


    /**
     * 缓存穿透封装处理
     *
     * @param keyPrefix
     * @param id
     * @param classType
     * @param dbFallback
     * @param time
     * @param unit
     * @param <R>
     * @param <ID>
     * @return
     */
    public <R, ID> R queryWithPassThough(
            String keyPrefix, ID id, Class<R> classType, Function<ID, R> dbFallback,
            Long time, TimeUnit unit) {
        String key = keyPrefix + id;
        String objectJson = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isNotBlank(objectJson)) {
            // 缓存命中，直接返回
            return JSONUtil.toBean(objectJson, classType);
        }
        // 用缓存空字符串处理缓存穿透问题
        if (objectJson != null) {
            return null; // 返回错误信息
        }

        // 不存在查询数据库
        R r = dbFallback.apply(id);
        // 数据库不存在，将空字符串写入缓存，下次返回给该类请求
        if (Objects.isNull(r)) {
            final String EMPTY_SPACE_STR = "";
            final Long CACHE_NULL_TTL = 30L;
            stringRedisTemplate.opsForValue().set(key, EMPTY_SPACE_STR, CACHE_NULL_TTL, TimeUnit.MINUTES);
            return null;// 返回错误信息
        }
        // 数据库存在，写入缓存
        stringRedisTemplate.opsForValue().set(key, (String) JSONUtil.toBean(objectJson, classType), time, unit);
        return r;
    }

    // 自定义线程池
    private static final ExecutorService CACHE_REBUILD_EXECUOR = Executors.newFixedThreadPool(10);

    /**
     * 使用逻辑过期时间处理缓存击穿
     * 注意：这里热点key数据是手动写到redis中的
     * @param keyPrefix
     * @param id
     * @param classType
     * @param dbFallback
     * @param time
     * @param unit
     * @param lockKeyPrefix
     * @param <R>
     * @param <ID>
     * @return
     */
    public <R, ID> R queryWithLogicalExpire(
            String keyPrefix, ID id, Class<R> classType, Function<ID, R> dbFallback,
            Long time, TimeUnit unit, String lockKeyPrefix) {
        String key = keyPrefix + id;
        String objectJson = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isBlank(objectJson)) {
            // 缓存中不存在，直接返回
            // redis热点key是手动写入的
            return null;
        }
        // 命中,解析出其中的数据
        RedisData redisData = JSONUtil.toBean(objectJson, RedisData.class);
        R r = JSONUtil.toBean((JSONObject) redisData.getData(), classType);
        LocalDateTime expireTime = redisData.getExpireTime();
        // 判断是否过期
        if (expireTime.isAfter(expireTime)) {
            // 未过期，直接返回缓存信息
            return r;
        }
        // 过期：获取互斥锁，重建缓存
        String localKey = lockKeyPrefix + id;
        boolean isLocak = tryLock(localKey);
        if (isLocak) {
            // 成功获取锁，开启线程重建缓存
            CACHE_REBUILD_EXECUOR.submit(() -> {
                try {
                    R r1 = dbFallback.apply(id);
                    // 写入redis
                    this.setWithLogicExpire(key, r1, time, unit);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    unloack(localKey);
                }
            });
        }
        // 当前线程先返回稍旧一点的数据
        return r;
    }

    // 通过redis的特殊设值模拟一个互斥锁
    private boolean tryLock(String key) {
        Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(key, "");
        return flag;
    }

    private void unloack(String key) {
        stringRedisTemplate.delete(key);
    }


    // 缓存击穿封装的类
    private class RedisData {
        private LocalDateTime expireTime;
        private Object data;

        public LocalDateTime getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(LocalDateTime expireTime) {
            this.expireTime = expireTime;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }


    // 通过Redis生成对应业务的全局ID：0+31位时间戳+32位秒内序列号
    private final static long BEGIN_TIMESTAMP = 1640995200L;
    // 位移位数
    private final static int COUNT_BITS = 32;
    public static long nextId(String keyPrefix){
        // 1. 生成时间戳
        LocalDateTime now = LocalDateTime.now();
        long nowSecond = now.toEpochSecond(ZoneOffset.UTC);
        long timestamp = nowSecond - BEGIN_TIMESTAMP;
        
        // 生成当日key，使用redis的自增进行记数
        String date = now.format(DateTimeFormatter.ofPattern("yyy:MM:dd"));
        long count = stringRedisTemplate.opsForValue().increment("icr:"+ keyPrefix+ ":" + date);

        // 拼接返回
        return timestamp << COUNT_BITS | count;
        
    }
}
