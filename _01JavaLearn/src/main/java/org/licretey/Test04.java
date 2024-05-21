package org.licretey;

public class Test04 {
    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        boolean[] l1 = new boolean[111110];
        boolean[] l2 = new boolean[111110];
        for (int i = 11; i < 111110; i++) {
            l1[i-11] = is01(i);
            l2[i-11] = is02(i);
        }
        System.out.println(l1);
        System.out.println(l2);
    }

    private static boolean is01(int i) {
        if( i <= 1) return false;
        for (int j = 2; j <= Math.sqrt(i); j++) {
            if(i % j == 0) return false;
        }
        return true;
    }

    private static boolean is02(int i) {
        if( i <= 1) return false;
        if( i % 2 == 0) return false;
        for (int j = 3; j <= Math.sqrt(i); j++) {
            if(i % j == 0) return false;
        }
        return true;
    }

}
