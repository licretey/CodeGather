package org.licretey.juc;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author 雷智民
 * @version 1.0
 * @description: <对此类的描述>
 * @date 2024/5/15 11:37
 */
public class UnsafeTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        // getDeclaredField作用
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe o = (Unsafe)theUnsafe.get(null);
        System.out.println(o);
    }
}
