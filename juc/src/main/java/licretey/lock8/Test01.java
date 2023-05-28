package licretey.lock8;

import java.util.concurrent.TimeUnit;


/**
 * synchronized 锁住的对象是方法的调用者
 * 两个方法同一个锁，谁先拿到谁执行
 *
 */
public class Test01 {
    public static void main(String[] args) throws InterruptedException {
        Phone ph = new Phone();
        new Thread(()->{
            ph.call();
        },"A").start();

        TimeUnit.SECONDS.sleep(1);


        new Thread(()->{
            ph.sendSms();
        },"B").start();

    }
}


class Phone {
    public synchronized void sendSms(){
        System.out.println("sendSms");
    }

    public synchronized void call(){
        System.out.println("call");
    }
}