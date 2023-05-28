package licretey.juc;

public class SaleTicketDemo01 {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        // 多线程操作
        new Thread(() -> {
            for (int i = 1; i < 60; i++){
                ticket.sale();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 1; i < 60; i++){
                ticket.sale();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 1; i < 60; i++){
                ticket.sale();
            }
        }, "C").start();

    }
}

class Ticket {
    private int number = 50;

    public synchronized void sale() {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出了" + (number--) + "票号，剩余：" + number);
        }
    }
}
