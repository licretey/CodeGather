package licretey.lock8;

import java.util.concurrent.TimeUnit;


/**
 * static 锁的是class , class是全局唯一的
 *
 */
public class Test03 {
    public static void main(String[] args) throws InterruptedException {
        Phone3 ph1 = new Phone3();
        Phone3 ph2 = new Phone3();
        new Thread(()->{
            try {
                ph1.sendSms();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"A").start();

        TimeUnit.SECONDS.sleep(1);


        new Thread(()->{
            ph2.call();
        },"B").start();

    }
}


class Phone3 {
    public static synchronized void sendSms() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("sendSms");
    }

    public static synchronized void call(){
        System.out.println("call");
    }

}