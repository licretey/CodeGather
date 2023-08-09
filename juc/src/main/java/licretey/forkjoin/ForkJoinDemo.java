package licretey.forkjoin;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class ForkJoinDemo extends RecursiveTask<Long> {
    private Long start ;
    private Long end;
    private Long side = 1_0000L; // 临界值：进入forkjoin计算


    public ForkJoinDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    //
    /**
     * 计算方法:
     *      大于临界值拆分，否则计算
     * 优化1：forkjoin计算
     *      1 继承extends RecursiveTask任务
     *      2 实现计算逻辑，forkjoinPool.execute(ForkTask task)
     * 优化2：并行流
     */
    @Override
    protected Long compute() {
        if((end-start)< side){
            Long sum = 0L;
            for (Long i = start; i <= end; i++) {
                sum += i  ;
            }
            return sum;
        }else {
            // forkjoin
            Long mid = (start+ end)/2;
            ForkJoinDemo fork1 = new ForkJoinDemo(start, mid);
            fork1.fork();// 把线程压入队列
            ForkJoinDemo fork2 = new ForkJoinDemo(mid+1, end);
            fork2.fork();// 把线程压入队列

            // 获取结果
            return fork1.join() + fork2.join();
        }
    }
}
