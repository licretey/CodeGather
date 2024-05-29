package test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Description:
 * @Date: 2024/5/22 13:09
 */
public class Test01 {
    private AtomicInteger a = new AtomicInteger();
    private LongAdder b = new LongAdder();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // if (extracted()) return;

        // threadTest01();

        // testCompletableFuture();


        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread());
            int a = 10 / 0;
            // });
            return 10;
        }).whenComplete((v, e) -> {
            if (e != null) {
                System.out.println("Ok!!!");
            }

        }).exceptionally(e -> {
            System.out.println(11111);
            return 0;
        });

        TimeUnit.SECONDS.sleep(2);

    }

    // 4大增强方法
    private static void testCompletableFuture() throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newCachedThreadPool();
        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread());
        // });
        },pool);
        System.out.println(cf.get());


        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread());
            // });
            return "aaa";
        },pool);
        System.out.println(cf2.get());
    }

    private static void threadTest01() throws InterruptedException {
        CountDownLatch cd = new CountDownLatch(3);
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep((long) (1 * Math.random()));
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                cd.countDown();
            });
            thread.start();
        }

        cd.await();
        System.out.println("自行");
        //

        FutureTask<String> task = new FutureTask<>(() -> {
            return "aa";
        });
        Thread thread = new Thread(task);
        thread.start();
        // Semaphore sp = new Semaphore(3);

        // future -> CompletableFuture
        //
    }

    private static boolean extracted() {
        Map<String, String> map = new HashMap<>();
        map.put("11", "aa");
        map.put("22", "bb");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
            if (entry.getKey().equals("11")) return true;
        }
        FutureTask<String> aa = new FutureTask<>(() -> {
        }, "aa");
        Thread thread = new Thread(aa, "");
        return false;
    }
}
