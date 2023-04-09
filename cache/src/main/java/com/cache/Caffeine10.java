package com.cache;

import com.cache.bean.User;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.time.Duration;

public class Caffeine10 {
    public static void main(String[] args) {
        new Caffeine10().baseDemo();
    }

    // 实现进程缓存

    @Resource
    private Cache<Long, User> userCache; // 注入缓存对象
    public User processCache(Long id ){
        // 使用缓存
        return userCache.get(id, key ->{
            User user = new User();
            user.setId(id);
            return user;
        });
    }

    // caffine的基本用法
    public void baseDemo(){
        // 驱除不是立即驱逐，而是做了一个标记，在下次读或写后或空闲时进行驱逐
        Cache<Object, Object> caffeine = Caffeine.newBuilder()
                .maximumSize(10) // 数量上限
                .expireAfterWrite(Duration.ofSeconds(100)) //留存时间
                .build();


        caffeine.put("keyStr","V1");

        Object keyStr = caffeine.getIfPresent("keyStr");
        Object keySt = caffeine.get("keySt", key -> {
            return "其它地方获取的值"; // 会被缓存
        });
    }


    @Configuration
    class cacheConfig{
        @Bean
        public Cache<Long, User> userCache(){
            return Caffeine.newBuilder()
                    .initialCapacity(100)
                    .maximumSize(10000)
                    .build();
        }

        @Bean
        public Cache<String, User> stockCache(){
            return Caffeine.newBuilder()
                    .initialCapacity(100)
                    .maximumSize(10000)
                    .build();
        }
    }
}
