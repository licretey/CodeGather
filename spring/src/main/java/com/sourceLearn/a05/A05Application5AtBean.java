package com.sourceLearn.a05;

import com.sourceLearn.a05.processor.AtBeanPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

import java.io.IOException;

// 常见BeanFactory的后处理器
public class A05Application5AtBean {
    public static void main(String[] args) throws IOException {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("config2", Config2.class);
        context.registerBean(AtBeanPostProcessor.class);
        // 初始化资源
        context.refresh();

        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }

        // 销毁容器 
        context.close();
    }
}