package org.licretey.juc;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author 雷智民
 * @version 1.0
 * @description: 自定义原子整数操作
 * @date 2024/5/15 13:24
 */
public class MyAtomicInteger {
    public static void main(String[] args) {

    }

    private static final Unsafe UNSAFE;
    private static final long valueOffset;
    // 涉及cas操作，需要使用volatile
    private static volatile int value;

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            UNSAFE = (Unsafe)theUnsafe.get(null);
            valueOffset = UNSAFE.objectFieldOffset(MyAtomicInteger.class.getDeclaredField("value"));
        } catch (NoSuchFieldException | IllegalAccessException e){
            throw new RuntimeException(e);
        }
    }

    public static int getValue() {
        return value;
    }


    public void increment() {

    }

    public void decrement(int amount) {
        while (true) {
            int prev = this.value;
            int next = prev - amount;
            if (UNSAFE.compareAndSwapInt(this, valueOffset, prev, next)) {
                break;
            }
        }
    }
}
