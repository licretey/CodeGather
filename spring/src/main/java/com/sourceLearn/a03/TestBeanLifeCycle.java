package com.sourceLearn.a03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Description: spring bean 生命周期
 * @Date: 2024/8/7 20:33
 */

@SpringBootApplication
public class TestBeanLifeCycle {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(TestBeanLifeCycle.class);
        run.close();
    }
}
