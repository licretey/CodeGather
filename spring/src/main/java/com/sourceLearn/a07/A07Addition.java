package com.sourceLearn.a07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @Description: 初始化和销毁：注意执行顺序
 * @Date: 2024/8/15 20:02
 */
@SpringBootApplication
public class A07Addition {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(A07Addition.class, args);
        context.close();
    }


    @Bean(initMethod = "init3")
    public Bean1 bean1(){
        return new Bean1();
    }

    @Bean(destroyMethod = "destory3")
    public Bean2 bean2(){
        return new Bean2();
    }
}
