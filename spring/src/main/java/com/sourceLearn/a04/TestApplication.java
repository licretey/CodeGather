package com.sourceLearn.a04;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.context.support.GenericApplicationContext;

public class TestApplication {
    public static void main(String[] args) {
        // 一个更干净的容器
        GenericApplicationContext ctx = new GenericApplicationContext();

        ctx.registerBean("bean1", Bean1.class);
        ctx.registerBean("bean2", Bean2.class);
        ctx.registerBean("bean3", Bean3.class);
        ctx.registerBean("bean4", Bean4.class);

        // @Autowired @Value
        ctx.getDefaultListableBeanFactory().setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver()); // 用于处理string
        ctx.registerBean(AutowiredAnnotationBeanPostProcessor.class);

        // @Resource @PostConstruct @PreDestory
        ctx.registerBean(CommonAnnotationBeanPostProcessor.class);

        // springboot注解
        ConfigurationPropertiesBindingPostProcessor.register(ctx.getDefaultListableBeanFactory());

        // 初始化容器：执行beanFactory后处理器，添加bean后处理器，初始化所有单例
        ctx.refresh();

        System.out.println(ctx.getBean(Bean4.class));

        // 销毁容器
        ctx.close();
        System.out.println(">>>>> over");
    }
}