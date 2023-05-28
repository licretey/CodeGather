package licretey.pc;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程之间的通信问题：生产者和消费者问题
 * 精确通知唤醒：A 通知B B通知C
 */
public class ProductorLock2 {
    public static void main(String[] args) {
        Data3 dt = new Data3();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                dt.printA();
            }
        },"A").start();


        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                dt.printB();
            }
        },"B").start();

        /**
         * 超过2个线程时，存在虚假唤醒问题，因为是通过if判断资源的（改成while即可）
         */
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                dt.printC();
            }
        },"C").start();
    }
}


/**
 * synchronized 版本的生产者消费者问题
 * 判断等待，业务，通知
 */

class Data3 {
    // 数字 代表资源
    private int num = 1;

    private Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    public void printA() {
        lock.lock();
        try {
            // 业务
            while (num!=1){
                // 等待
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName()+"=> AAA");
            // 唤醒B
            num = 2;
            condition2.signal();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();
        try {
            // 业务
            while (num!=2){
                // 等待
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName()+"=> BBB");
            // 唤醒C
            num = 3;
            condition3.signal();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void printC() {
        lock.lock();
        try {
            // 业务
            while (num!=3){
                // 等待
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName()+"=> CCC");
            // 唤醒A
            num = 1;
            condition1.signal();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
