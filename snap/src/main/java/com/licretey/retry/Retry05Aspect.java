package com.licretey.retry;

import com.licretey.retry.annotation.MyRetryable;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;


/**
 * 自定义注解重试：
 *      对使用了自定义注解地方切面，增强被修饰的方法
 */
@Slf4j
@Aspect
@Component
public class Retry05Aspect {
    // 切点：使用了自定义注解的地方
    @Pointcut("@annotation(com.licretey.retry.annotation.MyRetryable)")
    private void retryMethodCall(){}

    @Around("retryMethodCall()")
    public Object retry(ProceedingJoinPoint joinPoint) throws InterruptedException {
        // 获取使用注解时设置的重试次数和重试间隔
        MyRetryable retry = ((MethodSignature)joinPoint.getSignature()).getMethod().getAnnotation(MyRetryable.class);
        int maxRetryTimes = retry.retryTimes();
        int retryInterval = retry.retryInterval();

        Throwable error = new RuntimeException();
        for (int retryTimes = 1; retryTimes <= maxRetryTimes; retryTimes++){
            try {
                Object result = joinPoint.proceed();
                return result;
            } catch (Throwable throwable) {
                error = throwable;
                log.warn("调用发生异常，开始重试，retryTimes:{}", retryTimes);
            }
            Thread.sleep(retryInterval * 1000L);
        }
        throw new RuntimeException("重试次数耗尽", error);
    }


}
