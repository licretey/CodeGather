package com.sourceLearn.a05;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

// 常见BeanFactory的后处理器: @Bean
public class A05Application4AtBean {
    public static void main(String[] args) throws IOException {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("config2", Config2.class);
        // 1. 读取Config类中的配置信息
        // 用于读取类的元信息
        CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();
        // MetadataReader不走类加载，效率高于反射
        MetadataReader reader = factory.getMetadataReader(new ClassPathResource("com/sourceLearn/a05/Config2.class"));
        Set<MethodMetadata> beanMethods = reader.getAnnotationMetadata().getAnnotatedMethods(Bean.class.getName());
        for (MethodMetadata method : beanMethods) {
            System.out.println(method);
            Map<String, Object> attributes = method.getAnnotationAttributes(Bean.class.getName());
            String initMethod = (String) attributes.get("intiMethod");
            // 配置类@Bean标记的方法是工厂方法，构建时无需再执行类名信息(但需要对应工厂，用于执行方法)
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition();
            builder.setFactoryMethodOnBean(method.getMethodName(), "config2");
            // 设置对构造方法、工厂方法的自动装配方式
            builder.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);
            // 属性的注入
            if (initMethod != null && !initMethod.isEmpty()) {
                builder.setInitMethodName(initMethod);
            }
            AbstractBeanDefinition bd = builder.getBeanDefinition();
            // 无法处理带参数的Bean方法注入，所以SqlSessionFactoryBean无法注入（需要指定自动装配）
            context.getDefaultListableBeanFactory().registerBeanDefinition(method.getMethodName(), bd);


        }
        // 初始化资源
        context.refresh();

        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }

        // 销毁容器 
        context.close();
    }
}