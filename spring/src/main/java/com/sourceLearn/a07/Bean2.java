package com.sourceLearn.a07;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;

/**
 * @Description:
 * @Date: 2024/8/15 20:05
 */
public class Bean2 implements DisposableBean {
    @PreDestroy
    public void init() {
        System.out.println("销毁1");
    }

    public void destory3() {
        System.out.println("销毁3");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("销毁2");
        
    }
}
