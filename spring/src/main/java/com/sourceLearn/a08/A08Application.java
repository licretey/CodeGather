package com.sourceLearn.a08;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: scope
 * 5种类型：singleton（容器创建时创建，容器销毁时销毁）, prototype（每次使用时创建，销毁自己控制容器不管理）,
 *         request, session, globalSession（servletContext首次使用时创建）
 *
 * @Date: 2024/8/19 14:53
 */
@SpringBootApplication
public class A08Application {
    public static void main(String[] args) {
        SpringApplication.run(A08Application.class, args);
    }
}
