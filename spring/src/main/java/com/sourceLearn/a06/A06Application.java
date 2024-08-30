package com.sourceLearn.a06;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @Description:
 *  Aware扩展接口：用于注入以西的与容器相关的信息
 *
 *      BeanNameAware 注入bean的名字
 *      BeanFactoryAware 注入beanFactory的名字
 *      ApplicationContextAware 注入ApplicationContext容器
 *      EmbeddedValueResolverAware ${}
 *
 * Aware扩展接口与@Autowired的根本区别：
 *     Aware扩展接口是Spring容器内置的功能，不加任何扩展spring就可识别到，不会失效；
 *     而@Autowired的解析需要用到bean后处理器，属于扩展功能，某些情况下会失效
 *
 *  InitializingBean接口：
 *
 * @Date: 2024/8/15 17:30
 */
public class A06Application {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("myBean", MyBean.class);
        // context.registerBean(AutowiredAnnotationBeanPostProcessor.class);
        // context.registerBean(CommonAnnotationBeanPostProcessor.class);

        context.refresh();

        context.close();
    }
}
