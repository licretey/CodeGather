package licretey.callble;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch downLatch = new CountDownLatch(6); //减法器
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+" Go out");
                downLatch.countDown();
            }).start();
        }

        downLatch.await(); // 等待计数器归0，再向下执行
        System.out.println("close door");
    }
}
