package com.licretey;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.hash.ObjectHashMapper;

import javax.annotation.Resource;
import java.util.Map;



public class Redis05Template {
    @Resource
    private StringRedisTemplate template;


    // 需要导包
    // private final static ObjectMapper mapper = new ObjectHashMapper();
    // @Test
    // void testStr(){
    //     User user = new User("yang","19");
    //     //序列化
    //     String json = mapper.writeValueAsString(user);
    //     template.opsForValue().set("user:10",json);
    //     //反序列化
    //     String us = template.opsForValue().get("user:10");
    //     User user1 = mapper.readValue(us,User.class);
    // }

    @Test
    void testHash(){
        template.opsForHash().put("user:11","uname","yang");
        Map<Object, Object> entries = template.opsForHash().entries("user:11");
    }

    class User{
         private String name;
         private String age;

        public User(String name, String age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
             return name;
         }

         public void setName(String name) {
             this.name = name;
         }

         public String getAge() {
             return age;
         }

         public void setAge(String age) {
             this.age = age;
         }
     }
}
