package licretey.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test1();//4702
        test2();//4198
        test3();//128
    }

    // level1
    public static void test1(){
        Long sum = 0L;
        long start = System.currentTimeMillis();
        for (Long i = 1L; i <= 10_0000_0000L; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("sum = "+sum+" 时间："+(end-start));
    }

    public static void test2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ForkJoinTask<Long> forkTask = new ForkJoinDemo(1L, 10_0000_0000L);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // forkJoinPool.execute(forkTask);
        // forkTask.get();//阻塞式获取结果
        ForkJoinTask<Long> submit = forkJoinPool.submit(forkTask);
        Long sum = submit.get();//也是阻塞式获取结果
        long end = System.currentTimeMillis();
        System.out.println("sum = "+sum+" 时间："+(end-start));

    }

    // 进一步使用并行流 (]
    public static void test3(){
        long start = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(0L, 10_0000_0000L).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("sum = "+sum+" 时间："+(end-start));

    }
}
