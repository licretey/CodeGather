package licretey.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Demo01 {
    public static void main(String[] args) {
        // CompletableFuture<Void> future = CompletableFuture.runAsync(()->{
        //     try {
        //         TimeUnit.SECONDS.sleep(2);
        //     } catch (InterruptedException e) {
        //         e.printStackTrace();
        //     }
        //     System.out.println(Thread.currentThread().getName());
        // });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(()->{
            return 1024;
        });

        System.out.println(future2.whenComplete((t, u) -> {
            System.out.println(t); // 正常返回结果
            System.out.println(u); // 错误信息
        }).exceptionally((e) -> {
            System.out.println(e.getMessage());
            return 233; // 错误时的处理
        }));
        System.out.println("1111");
        // future.get(); // 阻塞获取结果


    }
}
