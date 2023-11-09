package com.http;

import java.nio.charset.StandardCharsets;

public class HttpEncode {

    public static void main(String[] args) {
        // 对乱码字符串的处理
        String errorStr = ""; // 默认情况下使用的是iso8859-1
        byte[] bytes = errorStr.getBytes(StandardCharsets.ISO_8859_1);
        String utf8Str = new String(bytes, StandardCharsets.UTF_8);
        System.out.println(utf8Str);
    }
}
