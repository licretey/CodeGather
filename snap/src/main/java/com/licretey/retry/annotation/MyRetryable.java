package com.licretey.retry.annotation;

import java.lang.annotation.*;

/**
 * 自定义重试注解
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyRetryable {
    // 最大重试次数
    int retryTimes() default 3;
    // 重试间隔 (x1000)
    int retryInterval() default 1;
}
