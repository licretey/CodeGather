package com.sourceLearn.a06;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Date: 2024/8/15 17:48
 */
@Configuration
public class MyConfig2 implements InitializingBean, ApplicationContextAware {

    // 添加beanFactory后处理器：会造成几个注解的解析失效
    @Bean
    public BeanFactoryPostProcessor processor2() {
        return beanFactory -> {
            System.out.println("beanFactory后处理器processor2执行");
        };
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("注入 ApplicationContext : " + applicationContext);

    }
}
