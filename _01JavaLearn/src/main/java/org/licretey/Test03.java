package org.licretey;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test03 {
    private static Random random = new Random();
    public static int random(int amount){
        return random.nextInt(amount)+1;
    }
    public static void main(String[] args) throws InterruptedException {
        TickWindow window = new TickWindow(1000);
        List<Thread> threads = new ArrayList<>();
        List<Integer> selled = new ArrayList<>();

        for(int i = 0; i < 1000; i++){
            Thread thread = new Thread(() -> {
                int amount = window.sell(random(3));
                selled.add(amount);
            });
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("selled: " + selled.stream().mapToInt(i->i).sum());
        System.out.println("conut: " + window.getConut());

     }

}
class  TickWindow {
    private int conut;

    public TickWindow(int conut) {
        this.conut = conut;
    }

    public int getConut() {
        return conut;
    }

    public int sell(int amount){

        if(amount > conut){
            return 0; // 库存不足
        }

        conut -= amount;
        return amount;
    }
}

