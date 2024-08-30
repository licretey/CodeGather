package com.sourceLearn.a05;

import com.sourceLearn.a05.processor.AtBeanPostProcessorConfig3;
import com.sourceLearn.a05.processor.AtMapperPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

import java.io.IOException;

// 常见BeanFactory的后处理器: @Mapper
public class A05Application7AtMapper {
    public static void main(String[] args) throws IOException {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("config3", Config3.class);
        context.registerBean(AtBeanPostProcessorConfig3.class);
        context.registerBean(AtMapperPostProcessor.class);
        // 初始化资源
        context.refresh();

        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }

        // 销毁容器 
        context.close();
    }
}