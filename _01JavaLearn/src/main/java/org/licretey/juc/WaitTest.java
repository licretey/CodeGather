package org.licretey.juc;

public class WaitTest {
    public static void main(String[] args) {
        Waitfy waitfy = new Waitfy(1, 5);
        new Thread(() -> {
            waitfy.print("A", 1, 2);
        }).start();
        new Thread(() -> {
            waitfy.print("B", 2, 3);
        }).start();
        new Thread(() -> {
            waitfy.print("C", 3, 1);
        }).start();
    }


}

class Waitfy {
    private int flag;
    private int loopNumber;

    public Waitfy(int flag, int loopNumber) {
        this.flag = flag;
        this.loopNumber = loopNumber;
    }

    public void print(String str, int waitFlag, int nextFlag) {
        for (int i = 0; i < this.loopNumber; i++) {
            synchronized (this) {

                while (this.flag != waitFlag) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.print(str);
                this.flag = nextFlag;
                this.notifyAll();
            }

        }
    }
}