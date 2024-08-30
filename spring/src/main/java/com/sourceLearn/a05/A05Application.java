package com.sourceLearn.a05;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

// 常见BeanFactory的后处理器
public class A05Application {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("config", Config.class);

        // 解析@ComponentScan @Bean @Import @ImportResource
        context.registerBean(ConfigurationClassPostProcessor.class);
        // 解析@MapperScan
        context.registerBean(MapperScannerConfigurer.class,bd->{
            // 需要执行解析路径
            bd.getPropertyValues().add("basePackage","com.sourceLearn.a05.mapper");
        });

        // 初始化资源
        context.refresh();

        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }

        // 销毁容器 
        context.close();
    }
}