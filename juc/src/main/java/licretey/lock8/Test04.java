package licretey.lock8;

import java.util.concurrent.TimeUnit;


/**
 * static 锁的是class , class是全局唯一的
 *
 */
public class Test04 {
    public static void main(String[] args) throws InterruptedException {
        Phone4 ph1 = new Phone4();
        new Thread(()->{
            try {
                ph1.sendSms();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"A").start();

        TimeUnit.SECONDS.sleep(1);


        new Thread(()->{
            ph1.call();
        },"B").start();

    }



}


class Phone4 {
    public static synchronized void sendSms() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("111 sendSms");
    }

    // 锁调用者
    public synchronized void call(){
        System.out.println("222 call");
    }

}