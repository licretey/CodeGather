package com.sourceLearn.a01;


import com.sourceLearn.a01.event.UserRegisteredEvent;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Locale;
import java.util.Map;

/**
 * @Description: BeanFactory、ApplicationContext(DefaultListableBeanFactory) Learn
 * @Date: 2024/8/5 14:13
 */
public class BeanFactoryLean {
    public static void main(String[] args) throws IOException {
        // beanFactory(args);
        applicationContext2(args);
    }

    private static void applicationContext(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(BeanFactoryLean.class, args);
        String hi = ctx.getMessage("hi", null, Locale.ENGLISH);// 获取翻译 Messagesource
        System.out.println(hi);
    }

    private static void applicationContext2(String[] args) throws IOException {
        ConfigurableApplicationContext ctx = SpringApplication.run(BeanFactoryLean.class, args);
        // 通配符
        // Resource[] resources = ctx.getResources("classpath:application.properties");
        // classpath* 会找jar包中的配置，否则只在项目路径下查找
        Resource[] resources = ctx.getResources("classpath*:/META-INF/spring.properties");
        for (Resource resource : resources) {
            System.out.println(resource);
        }

        // 获取系统配置信息
        ctx.getEnvironment().getProperty("java_home");
        ctx.getEnvironment().getProperty("server.port");

        // 发布事件
        ctx.publishEvent(new UserRegisteredEvent(ctx));
        // spring中任务组件都可以作为监听器：

    }

    private static void beanFactory(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(BeanFactoryLean.class, args);
        try {
            Field singletonObjects = DefaultSingletonBeanRegistry.class.getDeclaredField("singletonObjects");
            singletonObjects.setAccessible(true);
            ConfigurableListableBeanFactory beanFactory = ctx.getBeanFactory();
            Map<String, Object> map = (Map<String, Object>) singletonObjects.get(beanFactory);
            map.forEach((k, v) -> {
                System.out.println(k + "=" + v);
            });
        } catch (IllegalAccessException | NoSuchFieldException e) {
            System.out.println(e.getMessage());
        }
    }
}
