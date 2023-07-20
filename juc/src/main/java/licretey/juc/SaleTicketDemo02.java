package licretey.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaleTicketDemo02 {
    public static void main(String[] args) {
        Ticket2 ticket = new Ticket2();
        // 多线程操作
        new Thread(() -> {
            for (int i = 1; i < 60; i++) ticket.sale();
        }, "A").start();

        new Thread(() -> {
            for (int i = 1; i < 60; i++) ticket.sale();
        }, "B").start();

        new Thread(() -> {
            for (int i = 1; i < 60; i++) ticket.sale();
        }, "C").start();

    }
}

class Ticket2 {
    private int number = 50;

    Lock lock = new ReentrantLock();

    public synchronized void sale() {
        // 加锁
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出了" + (number--) + "票号，剩余：" + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
