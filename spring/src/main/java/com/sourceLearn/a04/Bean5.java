package com.sourceLearn.a04;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;


public class Bean5 {
//    private static final Logger log = LoggerFactory.getLogger(Bean1.class);

    private Bean2 bean2;

    @Autowired
    public void setBean2(Bean2 bean2) {
        System.out.println("<<<< @Autowired生效：" + bean2);
        this.bean2 = bean2;
    }


    private Bean3 bean3;

    @Resource
    public void setBean3(Bean3 bean3) {
        System.out.println("<<<< @Resource生效：" + bean3);
        this.bean3 = bean3;
    }


    @PostConstruct
    public void init() {
        System.out.println("<<<< @PostConstruct：");
    }

    @PreDestroy
    public void destory() {
        System.out.println("<<<< @PreDestroy");
    }

    @Override
    public String toString() {
        return "Bean1{" +
                "bean2=" + bean2 +
                ", bean3=" + bean3 +
                '}';
    }
}