package com.sourceLearn.a05.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * @Description: @Bean的beanFactory后处理器
 * @Date: 2024/8/15 15:31
 */
public class AtBeanPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        try{
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
                if (configurableListableBeanFactory instanceof DefaultListableBeanFactory beanFactory) {
                    beanFactory.registerBeanDefinition(method.getMethodName(), bd);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }

    }
}
