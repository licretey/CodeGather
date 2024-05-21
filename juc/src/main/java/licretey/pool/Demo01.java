package licretey.pool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Demo01 {
    public static void main(String[] args) {
        // ExecutorService executorService = Executors.newSingleThreadExecutor(); // 单个线程
        ExecutorService executorService = Executors.newFixedThreadPool(5); // 固定线程池大小
        ExecutorService executorService3 = Executors.newCachedThreadPool(); // 可伸缩线程池

        try {
            for (int i = 0; i < 1000; i++) {
                executorService3.execute(()->{
                    System.out.println(Thread.currentThread().getName()+" ok");
                });
            }

            // 线程池使用完需要关闭
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }
}
