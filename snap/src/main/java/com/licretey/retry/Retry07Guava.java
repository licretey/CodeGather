// package com.licretey.retry;
//
// import com.github.rholder.retry.*;
// import com.licretey.retry.orderservice.OrderService;
//
// import java.util.Objects;
// import java.util.concurrent.TimeUnit;
//
// public class Retry07Guava implements OrderService {
//
//     public String guavaRetry(Integer num) {
//         Retryer retryer = RetryerBuilder.newBuilder()
//                 //无论出现什么异常，都进行重试
//                 .retryIfException()
//                 //返回结果为 error时，进行重试
//                 .retryIfResult(result -> Objects.equals(result, "error"))
//                 //重试等待策略：等待 2s 后再进行重试
//                 .withWaitStrategy(WaitStrategies.fixedWait(2, TimeUnit.SECONDS))
//                 //重试停止策略：重试达到 3 次
//                 .withStopStrategy(StopStrategies.stopAfterAttempt(3))
//                 .withRetryListener(new RetryListener() {
//                     @Override
//                     public void onRetry(Attempt attempt) {
//                         System.out.println("RetryListener: 第" + attempt.getAttemptNumber() + "次调用");
//                     }
//                 })
//                 .build();
//         try {
//             retryer.call(() -> testGuavaRetry(num));
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//         return "test";
//     }
//
//     @Override
//     public void addOrder() {
//         this.guavaRetry(3);
//     }
//
//     private void testGuavaRetry(int num){}
// }
