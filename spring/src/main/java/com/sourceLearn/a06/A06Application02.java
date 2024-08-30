package com.sourceLearn.a06;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @Description:
 *  Aware扩展接口某些情况下失效：
 *    refresh()加载流程：
 *    1.查询beanFactory后处理器
 *    2.添加bean后处理器
 *    3.初始化单例
 *
 * @Date: 2024/8/15 17:30
 */
public class A06Application02 {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        // context.registerBean("myConfig1", MyConfig1.class);
        context.registerBean("myConfig2", MyConfig2.class);
        context.registerBean(AutowiredAnnotationBeanPostProcessor.class);
        context.registerBean(CommonAnnotationBeanPostProcessor.class);
        context.registerBean(ConfigurationClassPostProcessor.class);

        context.refresh();
        context.close();
    }
}
