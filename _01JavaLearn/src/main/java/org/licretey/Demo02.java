package org.licretey;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class Demo02 {
    public static void main(String[] args) {
        // List<String> list = new  LinkedList<>();
        // ArrayList<String> strings = new ArrayList<>(list);
        // ArrayList<Number> numbers = new ArrayList<>();
        // Number n = 1;

        // int i;
        // test test = new test();
        // System.out.println(test.i);
        // System.out.println(new BigDecimal());
        // System.getProperties().list(System.out);
        // testFor();


        String validBase64 = "SGVsbG8gd29ybGQh";  // "Hello world!"
        String invalidBase64 = "SGVsbG8gd29ybGQh!";  // Contains an invalid character "!"
        String invalidPadding = "SGVsbG8gd29ybGQ";  // Missing padding "="
        String invalidStr = "root";  // Missing padding "="

        // System.out.println(isValidBase64(validBase64)); // true
        // System.out.println(isValidBase64(invalidBase64)); // false
        // System.out.println(isValidBase64(invalidPadding)); // false
        System.out.println(isValidBase64(invalidStr)); // false
    }




    public static boolean isValidBase64(String input) {
        if (input.length() % 4 != 0) {
            return false;
        }

        String base64Pattern = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";
        boolean isLegal = input.matches(base64Pattern);

        if(!isLegal) return false;
        try {
            String decStr = new String(Base64.getDecoder().decode(input.getBytes()), StandardCharsets.UTF_8);
            char[] passArr = decStr.toCharArray();
            for (char tempChar : passArr) {
                byte byteAscii = (byte) tempChar;
                if (byteAscii < 32 || byteAscii > 126) return false;
            }
        } catch (Exception e){
            return false;
        }
        return true;
    }



    /**
     * +@FunctionalInterface：函数式接口标志
     * + 有且只能一个抽象方法：abstract修饰
     * + 没有abstract修饰的
     *     + 一种是对父类方法的重写
     *     + 二是有default修饰，并当即进行了实现（非抽象方法
     */

    private static void testFor(){
        List<String> list = Arrays.asList("yang","jian","ping","hahaha","null","damei");
        System.out.println(list);
        System.out.println();


        list.forEach( str -> {
            if(str.equals("ping")) return;
            System.out.println(str);
        });

        System.out.println();
        for (String s : list){
            if(s.equals("hahaha")) return;

            System.out.println(s);
        }

        Integer a = new Integer(1);
    }

}


