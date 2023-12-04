// package com.config;
//
// import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
//
// /**
//  * @description Redis配置
//  * 需要导包
//  */
// @Configuration
// public class RedisConfig {
//
//     @Autowired
//     private RedisConnectionFactory factory;
//
//     @Bean
//     @SuppressWarnings(value = { "unchecked", "rawtypes" })
//     public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory)
//     {
//         RedisTemplate<Object, Object> template = new RedisTemplate<>();
//         template.setConnectionFactory(connectionFactory);
//
//         FastJsonRedisSerializer serializer = new FastJsonRedisSerializer(Object.class);
//
//         // 使用StringRedisSerializer来序列化和反序列化redis的key值
//         template.setKeySerializer(new StringRedisSerializer());
//         template.setValueSerializer(serializer);
//
//         // Hash的key也采用StringRedisSerializer的序列化方式
//         template.setHashKeySerializer(new StringRedisSerializer());
//         template.setHashValueSerializer(serializer);
//
//         template.afterPropertiesSet();
//         return template;
//     }
// }