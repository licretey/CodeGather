package org.redis;// package org.redis;
//
// @Configuration
// public class RedisConfig {
//
//     //读取配置文件中的redis的ip地址
//     @Value("${spring.redis.host:disabled}")
//     private String host;
//
//     @Value("${spring.redis.port:0}")
//     private int port;
//
//     @Value("${spring.redis.database:0}")
//     private int database;
//
//     @Bean
//     public RedisUtil getRedisUtil(){
//         if(host.equals("disabled")){
//             return null;
//         }
//         RedisUtil redisUtil=new RedisUtil();
//         redisUtil.initPool(host,port,database);
//         return redisUtil;
//     }
//
// }
