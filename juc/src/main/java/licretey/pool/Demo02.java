package licretey.pool;

import java.util.concurrent.*;

public class Demo02 {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 5,
                4, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        //获取电脑核数
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
