package com.licretey.retry;

import com.licretey.retry.annotation.MyRetryable;
import com.licretey.retry.orderservice.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Spring自带重试机制：
 *      添加完依赖后，需要在启动类上通过 @EnableRetry 开启重试，然后在需要重试的方法上添加@Retryable
 *       @Retryable ：指定异常重试、次数
 *          backoff : 设置重试补偿机制，可以设置重试间隔，并且支持设置重试延迟倍数
 *          include exclude ：包含或排除哪些异常进行重试
 *          value ：只对特定类型的异常进行重试。默认：所有异常
 *          label ：设置该重试的唯一标志，用于统计输出
 *       如@Backoff(delay = 2000, multiplier = 2) 第1次重试间隔2s,第2次重试间隔4s,...8s
 *       @Backoff ：指定重试回退策略
 *       @Recover ：进行善后工作：当重试达到指定次数之后，会调用指定的方法来进行日志记录等操作
 * 注意：
 *      @Recover 注解标记的方法必须和被@Retryable标记的方法在同一个类中
 *      重试方法抛出的异常类型需要与recover()方法参数类型保持一致
 *      recover()方法返回值需要与重试方法返回值保证一致
 *      recover()方法中不能再抛出 Exception，否则会报无法识别该异常的错误
 * 缺点
 *      Spring的重试机制只支持对 异常 进行捕获，而无法对返回值进行校验
 */
public class Retry06SpringRetry implements OrderService {
    private static final Logger log = LoggerFactory.getLogger(Retry06SpringRetry.class);

    @Override
    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 2000, multiplier = 2))
    public void addOrder() {
        int i = 3/0;
        // addOrder
    }

    @Recover
    public void recover(RuntimeException e) {
        log.error("达到最大重试次数", e);
    }


    /**
     * Spring Retry 也支持直接在调用时使用代码进行重试
     *
     * 此时唯一的好处是可以设置多种重试策略：
     *
     * NeverRetryPolicy：只允许调用RetryCallback一次，不允许重试
     *
     * AlwaysRetryPolicy：允许无限重试，直到成功，此方式逻辑不当会导致死循环
     *
     * SimpleRetryPolicy：固定次数重试策略，默认重试最大次数为3次，RetryTemplate默认使用的策略
     *
     * TimeoutRetryPolicy：超时时间重试策略，默认超时时间为1秒，在指定的超时时间内允许重试
     *
     * ExceptionClassifierRetryPolicy：设置不同异常的重试策略，类似组合重试策略，区别在于这里只区分不同异常的重试
     *
     * CircuitBreakerRetryPolicy：有熔断功能的重试策略，需设置3个参数openTimeout、resetTimeout和delegate
     *
     * CompositeRetryPolicy：组合重试策略，有两种组合方式，乐观组合重试策略是指只要有一个策略允许即可以重试，悲观组合重试策略是指只要有一个策略不允许即可以重试，但不管哪种组合方式，组合中的每一个策略都会执行
     */
    // public void normalSpringRetry() {
    //     // 表示哪些异常需要重试,key表示异常的字节码,value为true表示需要重试
    //     Map<Map, Boolean> exceptionMap = new HashMap<>();
    //     exceptionMap.put(HelloRetryException.class, true);
    //
    //
    //     // 构建重试模板实例
    //     RetryTemplate retryTemplate = new RetryTemplate();
    //
    //     // 设置重试回退操作策略，主要设置重试间隔时间
    //     FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
    //     long fixedPeriodTime = 1000L;
    //     backOffPolicy.setBackOffPeriod(fixedPeriodTime);
    //
    //     // 设置重试策略，主要设置重试次数
    //     int maxRetryTimes = 3;
    //     SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy(maxRetryTimes, exceptionMap);
    //
    //     retryTemplate.setRetryPolicy(retryPolicy);
    //     retryTemplate.setBackOffPolicy(backOffPolicy);
    //
    //     Boolean execute = retryTemplate.execute(
    //             //RetryCallback
    //             retryContext -> {
    //                 String hello = helloService.hello();
    //                 log.info("调用的结果:{}", hello);
    //                 return true;
    //             },
    //             // RecoverCallBack
    //             retryContext -> {
    //                 //RecoveryCallback
    //                 log.info("已达到最大重试次数");
    //                 return false;
    //             }
    //     );
    // }
}
