package licretey.callble;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
    public static void main(String[] args) {
        // 线程最大数量
        Semaphore semaphoreDemo = new Semaphore(3);

        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                try {
                    semaphoreDemo.acquire();
                    System.out.println(Thread.currentThread().getName()+"抢到");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"释放");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    semaphoreDemo.release();
                }
            }).start();
        }
    }
}
