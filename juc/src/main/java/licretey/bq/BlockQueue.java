package licretey.bq;

import java.util.concurrent.ArrayBlockingQueue;

public class BlockQueue {
    public static void main(String[] args) {

    }

    // 抛出异常
    public void test1(){
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
        for (int i = 0; i < 3; i++) {
            System.out.println(blockingQueue.add(i));
        }
        for (int i = 0; i < 3; i++) {
            // NoSuchElementException
            System.out.println(blockingQueue.remove());
        }
    }


    public void test2(){
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
        for (int i = 0; i < 3; i++) {
            System.out.println(blockingQueue.offer(i));
        }
        for (int i = 0; i < 3; i++) {
            // NoSuchElementException
            System.out.println(blockingQueue.poll());
        }
    }
}
