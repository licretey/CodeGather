package com.sourceLearn.a06;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Date: 2024/8/15 17:48
 */
@Configuration
public class MyConfig1 {

    @Autowired
    public void setApplication(ApplicationContext applicationContext) {
        System.out.println("注入 ApplicationContext : "+applicationContext);
    }

    @PostConstruct
    public void init() {
        System.out.println("初始化");
    }

    // 添加beanFactory后处理器：会造成几个注解的解析失效
    @Bean
    public BeanFactoryPostProcessor processor1(){
        return beanFactory -> {
            System.out.println("beanFactory后处理器processor1执行");
        };
    }


}
