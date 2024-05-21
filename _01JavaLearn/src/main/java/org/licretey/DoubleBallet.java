package org.licretey;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

public class DoubleBallet {
    public static void main(String[] args) {
        Random ran = new Random();
        int blueBall = ran.nextInt(16) + 1;
        TreeSet<Object> redBalls = new TreeSet<>();
        new DoubleBallet().generate(ran, redBalls);

        System.out.println("R:"+redBalls);
        System.out.println("B:"+blueBall);
    }

    private void generate(Random ran, TreeSet<Object> redBalls){
        if(!redBalls.isEmpty()) redBalls.clear();
        while (redBalls.size()<6){
            int ball =  ran.nextInt(30)+1+3;
            if(ball<31) redBalls.add(ball);
        }
        ArrayList<Object> ballList = new ArrayList<>(redBalls);
        for (int i = 0; i < ballList.size(); i++) {
            if(i == 0) continue;
            int compareResult = Integer.valueOf(ballList.get(i).toString()) - Integer.valueOf(ballList.get(i - 1).toString());
            if(compareResult == 1) {
                this.generate(ran, redBalls);
                break;
            }
        }
    }
}
