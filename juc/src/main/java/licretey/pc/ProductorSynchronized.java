package licretey.pc;


/**
 * 线程之间的通信问题：生产者和消费者问题
 * 等待唤醒，通知唤醒
 * 线程交替执行：A B 操作同一个变量 num
 *      A num+1
 *      B num-1
 *
 *  超过2个线程时，存在虚假唤醒问题，因为是通过if判断资源的（改成while即可）
 *      线程A获取锁后,第1次符合条件执行++，通知了BC但因为没释放锁，BC的访问被拒进入等待，
 *  A第2次不符合条件进入等待，A释放锁，BC竞争
 *      B大概率竞争成功，B第1次符合条件--，通知了CA但因为没释放锁, CA的访问被拒进入等待，
 *  B第2次不符合条件进入等待，B释放锁，CA竞争
 *      假定C竞争成功，C第1次符合条件执行++，通知了AB但因为没释放锁，AB的访问被拒进入等待，
 *  C第2次不符合条件进入等待，C释放锁，AB竞争
 *      假定A竞争成功，唤醒后直接跳过A的第2次条件判断，直接执行执行++，并通知了BC但因为没释放锁，BC的访问被拒进入等待，
 *  A第3次不符合条件进入等待，A释放锁，BC竞争
 *  *
 */
public class ProductorSynchronized {
    public static void main(String[] args) {
        Data dt = new Data();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    dt.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"A").start();


        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    dt.decrement();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"B").start();

        /**
         * 超过2个线程时，存在虚假唤醒问题，因为是通过if判断资源的（改成while即可）
         */
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    dt.decrement();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"B").start();
    }
}


/**
 * synchronized 版本的生产者消费者问题
 * 判断等待，业务，通知
 */

class Data {
    // 数字 代表资源
    private int num = 0;

    public synchronized void increment() throws InterruptedException {
        while (num!=0){
            // 等待
            this.wait();
        }
        // 通知其他线程，已+1
        num ++;
        System.out.println(Thread.currentThread().getName()+"=>"+num);
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        while (num==0){
            // 等待
            this.wait();
        }
        // 通知其他线程，已减1
        num --;
        System.out.println(Thread.currentThread().getName()+"=>"+num);
        this.notifyAll();
    }
}
