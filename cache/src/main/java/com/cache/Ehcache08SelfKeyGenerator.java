package com.cache;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.google.common.hash.Hashing;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

public class Ehcache08SelfKeyGenerator implements KeyGenerator {
    /**
     * 缓存的key，spel书写：
     *   1. 有时候参数写的比较麻烦
     *   2. 可能忘记了要写的key表达式
     *   3. key值非空则直接使用，否则就使用配置的keyGenerator
     */
    @Override
    public Object generate(Object target, Method method, Object... params) {
        StringBuilder finalKey = new StringBuilder();
        finalKey.append(target.getClass().getName()).append(".")
                .append(method.getName()).append(".");
        if(params == null || params.length == 0){
            return finalKey.append(0).toString();
        }
        for (Object param : params){
            if(param ==null){
                finalKey.append("null");
            }else if(ClassUtils.isPrimitiveArray(param.getClass())){
                // 如果一个参数由8种基本类型组成的数组
                int arrLength = Array.getLength(param);
                for (int i = 0; i < arrLength; i++) {
                    finalKey.append(Array.get(param,i)).append(",");
                }
            }else if(ClassUtils.isPrimitiveOrWrapper(param.getClass())
                || param instanceof String){
                // isPrimitiveOrWra pper: 8种基本类型+8种基本类型的包装类型
                finalKey.append(param);
            }else {
                String jsonString = JSON.toJSONString(param);
                // 其它情况分配一个随机id
                long murmur = Hashing.murmur3_128()
                        .hashString(jsonString, StandardCharsets.UTF_8).asLong();
                finalKey.append(murmur);
            }
            // 分割多个参数
            finalKey.append("-");
        }
        System.out.println("最终的key："+finalKey.toString());
        return null;
    }
}
