package com.sourceLearn.a06;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Description:
 * @Date: 2024/8/15 17:33
 */
public class MyBean implements BeanNameAware, ApplicationContextAware, InitializingBean {
    @Override
    public void setBeanName(String name) {
        System.out.println("当前bean：" + this + " 的名字叫：" + name);

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("当前bean：" + this + " 的容器是：" + applicationContext);
    }

    @Autowired
    public void aaa(ApplicationContext applicationContext){
        // 需要加bean后处理器
        String s = new String("111");
        System.out.println("当前bean：" + this + " 使用@Autowired注入的容器是：" + applicationContext);
    }

    // 回调完Aware接口后执行初始化操作
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("当前bean：" + this + " 初始化");
    }

    @PostConstruct
    public void init(){
        // 需要加bean后处理器
        System.out.println("当前bean：" + this + " 使用@PostConstruct初始化");
    }
}


