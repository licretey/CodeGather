package com.sourceLearn.a05.processor;

import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;

/**
 * @Description: @Mapper的beanFactory后处理器
 * BeanFactoryPostProcessor提供的ConfigurableListableBeanFactory没有registerBeanDefinition，需要类型转换
 * 可以使用BeanDefinitionRegistryPostProcessor
 * @Date: 2024/8/15 15:31
 */
public class AtMapperPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanFactory) throws BeansException {
        try {
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resolver.getResources("classpath*:com/sourceLearn/a05/component/**/*.class");
            // 用于读取类的元信息
            CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();
            // 生成bean的名称工具类
            AnnotationBeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();
            for (Resource resource : resources) {
                MetadataReader reader = factory.getMetadataReader(resource);
                ClassMetadata classMetadata = reader.getClassMetadata();
                if (classMetadata.isInterface()) {
                    AbstractBeanDefinition bd = BeanDefinitionBuilder
                            // 实际mapper对象的封装类
                            .genericBeanDefinition(MapperFactoryBean.class)
                            // 接口名称
                            .addConstructorArgValue(classMetadata.getClassName())
                            // 设置自动装配
                            .setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE)
                            .getBeanDefinition();
                    // 为了生成bean名字，防止重名覆盖
                    AbstractBeanDefinition bd2 = BeanDefinitionBuilder.genericBeanDefinition(classMetadata.getClassName()).getBeanDefinition();
                    String beanName = beanNameGenerator.generateBeanName(bd2, beanFactory);
                    beanFactory.registerBeanDefinition(beanName, bd);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
