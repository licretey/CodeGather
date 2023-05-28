package licretey.pc;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程之间的通信问题：生产者和消费者问题
 * 等待唤醒，通知唤醒
 * 线程交替执行：A B 操作同一个变量 num
 *      A num+1
 *      B num-1
 */
public class ProductorLock {
    public static void main(String[] args) {
        Data2 dt = new Data2();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                dt.increment();
            }
        },"A").start();


        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                dt.increment();
            }
        },"B").start();

        /**
         * 超过2个线程时，存在虚假唤醒问题，因为是通过if判断资源的（改成while即可）
         */
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                dt.increment();
            }
        },"C").start();
    }
}


/**
 * synchronized 版本的生产者消费者问题
 * 判断等待，业务，通知
 */

class Data2 {
    // 数字 代表资源
    private int num = 0;

    private Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void increment() {
        lock.lock();
        try {
            // 业务
            while (num!=0){
                // 等待
                condition.await();
            }
            // 通知其他线程，已+1
            num ++;
            System.out.println(Thread.currentThread().getName()+"=>"+num);
            condition.signalAll();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public synchronized void decrement() throws InterruptedException {
        lock.lock();
        try {
            // 业务
            while (num!=0){
                // 等待
                condition.await();
            }
            // 通知其他线程，已-1
            num --;
            System.out.println(Thread.currentThread().getName()+"=>"+num);
            condition.signalAll();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
