package com.sourceLearn.a07;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.InitializingBean;

/**
 * @Description:
 * @Date: 2024/8/15 20:05
 */
public class Bean1 implements InitializingBean {
    @PostConstruct
    public void init() {
        System.out.println("初始化1");
    }

    // 初始化1 初始化2中间执行aware接口

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化2");
    }

    public void init3() {
        System.out.println("初始化3");
    }
}
