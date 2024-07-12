package com.thread;/**
 * @description: 对此类的描述
 * @date 2024/6/6 14:05
 * @version 1.0
 */

import ch.qos.logback.core.util.TimeUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Date: 2024/6/6 14:05
 */
public class SynTest {
    private synchronized void test01(){
        // System.out.println(LocalDateTime.now() + " 010101");
        // try {
        //     TimeUnit.MILLISECONDS.sleep(3000);
        // } catch (InterruptedException e) {
        //     throw new RuntimeException(e);
        // }
        BigDecimal d = new BigDecimal("10");
        test(d);
        System.out.println(d);

    }


    private void test(BigDecimal test) {
        test = test.subtract(new BigDecimal("3"));
    }
    private synchronized void test02(){
        System.out.println(LocalDateTime.now()+" 020202");
    }


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i <10; i++) {
            System.out.println("外部 i:"+i);
            for (int j = 0; j < 10; j++) {
                System.out.println("内部 j:"+j);
                if(j == 3){
                    break;
                }
            }
        }


        SynTest synTest = new SynTest();
        new Thread(()->{
            synTest.test01();
        },"a").start();

        TimeUnit.MILLISECONDS.sleep(2000);
        System.out.println("sleeped");

        new Thread(()->{
            synTest.test02();
        },"b").start();
    }
}
