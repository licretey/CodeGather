package com.sourceLearn.a05;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;

// 常见BeanFactory的后处理器
public class A05Application2 {
    public static void main(String[] args) throws IOException {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("config", Config.class);
        // 1. 查询类上的指定注解
        ComponentScan componentScan = AnnotationUtils.findAnnotation(Config.class, ComponentScan.class);
        if(componentScan != null){
            for (String p : componentScan.basePackages()) {
                System.out.println(p); // com.sourceLearn.a05.component
                // 包名转资源表达式 com.sourceLearn.a05.component -> classpath*:com/sourceLearn/a05/component/**/*.class
                String path = "classpath*"+p.replace(".","/")+"/**/*.class";
                System.out.println(path);
                // 执行扫描的路径不是源代码的相对路径，而是class目录下的二进制字节文件（component目录下即使Bean4没有@Component注解也会被扫描到）
                Resource[] resources = context.getResources(path);
                // 用于读取类的元信息
                CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();
                // 生成bean的名称工具类
                AnnotationBeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();
                DefaultListableBeanFactory beanFactory = context.getDefaultListableBeanFactory();
                for (Resource resource : resources) {
                    // System.out.println(resource);
                    MetadataReader reader = factory.getMetadataReader(resource);
                    System.out.println("类名："+reader.getClassMetadata().getClassName());
                    AnnotationMetadata annotationMetadata = reader.getAnnotationMetadata();
                    System.out.println("是否有 @Component ："+annotationMetadata.hasAnnotation(ComponentScan.class.getName()));
                    System.out.println("是否有 @Component 派生注解："+annotationMetadata.hasMetaAnnotation(ComponentScan.class.getName()));

                    // 2. 读取类信息注入到beanFactory
                    if(annotationMetadata.hasAnnotation(ComponentScan.class.getName()) ||
                            annotationMetadata.hasMetaAnnotation(ComponentScan.class.getName())){
                        AbstractBeanDefinition bd = BeanDefinitionBuilder
                                .genericBeanDefinition(reader.getClassMetadata().getClassName())
                                .getBeanDefinition();
                        String beanName = beanNameGenerator.generateBeanName(bd, beanFactory);
                        beanFactory.registerBeanDefinition(beanName,bd);
                    }
                }
            }
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