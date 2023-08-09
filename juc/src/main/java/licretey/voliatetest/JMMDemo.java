package licretey.voliatetest;

import java.util.concurrent.TimeUnit;

public class JMMDemo {
    // 不加volatile会死循环
    private volatile static int  num = 0;
    public static void main(String[] args) throws InterruptedException {

    }

    public static void test01() throws InterruptedException {
        new Thread(()->{
            while (num==0){

            }
        }).start();

        TimeUnit.SECONDS.sleep(1);

        num = 1;
        System.out.println(num);
    }

    /**
     * 不保证原子性测试
     *  synchronized可以保证, 或者改用原子性的类来保证
     *  volatile不保证
     */
    public static void test02(){
        int num = 0;
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }

        while (Thread.activeCount()>2){
            Thread.yield();
        }
    }

    public static void add(){
        num ++;
    }
}
