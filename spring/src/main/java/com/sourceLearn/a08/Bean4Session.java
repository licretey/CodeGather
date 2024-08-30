package com.sourceLearn.a08;

import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @Description: 不同浏览；超时会被自动被销毁
 * @Date: 2024/8/19 15:23
 */
@Scope("session")
@Component
public class Bean4Session {
    @PreDestroy
    public void destroy(){
        System.out.println("Bean4Session destroy");
    }

    // 防止spring通过反射调用jdk中的方法，出现illegalAccessException
    @Override
    public String toString() {
        return "Bean4Session{}";
    }
}

