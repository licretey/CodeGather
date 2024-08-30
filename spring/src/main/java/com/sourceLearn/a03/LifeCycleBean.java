package com.sourceLearn.a03;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Date: 2024/8/7 20:33
 */
@Component
public class LifeCycleBean {
    public static final Logger log = LoggerFactory.getLogger(LifeCycleBean.class);

    public LifeCycleBean() {
        System.out.println("构造");
        log.debug("构造"); // no.1
    }

    @Autowired
    public void autowire(@Value("${JAVA_HOME}") String home) {
        System.out.println("依赖注入" + home);
        log.debug("依赖注入：{}", home); // no.2
    }


    @PostConstruct
    public void init() {
        System.out.println("初始化");
        log.debug("初始化"); // no.3
    }


    @PreDestroy
    public void destory() {
        System.out.println("销毁");
        log.debug("销毁"); // no.4 (单例)
    }

}
