package licretey.completableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Description:
 * @Date: 2024/8/29 14:33
 */
public class CFuture01 {
    public static void main(String[] args) throws Exception {
        demo01();
    }

    private static void demo01() throws InterruptedException, ExecutionException {
        FutureTask<String> t1 = new FutureTask<String>(() -> {
            System.out.println(Thread.currentThread() + "come in callable");
            return "hello callable";
        });
        new Thread(t1).start();
        System.out.println(Thread.currentThread() + t1.get());
    }
    // Runnable、Future
    //    ||
    // RunnableFuture
    //   ||
    // FutureTask(Callable)
    //  ||
    // CompletableFuture
    CompletableFuture<String> future = CompletableFuture.supplyAsync(()->{
        return "hello";
    }).thenApply(String::toUpperCase);
}

class MyThread implements Runnable {

    @Override
    public void run() {

    }
}


class MyThread2 implements Callable<String> {

    @Override
    public String call() throws Exception {
        return "";
    }
}
