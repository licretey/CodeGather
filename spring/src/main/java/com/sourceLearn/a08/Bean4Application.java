package com.sourceLearn.a08;

import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @Description: 不同后台服务
 * @Date: 2024/8/19 15:23
 */
@Scope("application")
@Component
public class Bean4Application {
    @PreDestroy
    public void destroy(){
        System.out.println("Bean4Application destroy");
    }


    // 防止spring通过反射调用jdk中的方法，出现illegalAccessException
    @Override
    public String toString() {
        return "Bean4Application{}";
    }
}