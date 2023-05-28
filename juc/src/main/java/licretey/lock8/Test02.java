package licretey.lock8;

import java.util.concurrent.TimeUnit;


/**
 * 没有锁直接执行
 *
 */
public class Test02 {
    public static void main(String[] args) throws InterruptedException {
        Phone2 ph1 = new Phone2();
        Phone2 ph2 = new Phone2();
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


class Phone2 {
    public synchronized void sendSms() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("sendSms");
    }

    public synchronized void call(){
        System.out.println("call");
    }

    public void hello(){
        System.out.println("hello ");
    }
}