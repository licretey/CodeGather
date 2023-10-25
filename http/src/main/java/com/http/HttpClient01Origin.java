package com.http;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class HttpClient01Origin {

    /*
     *  jdk原生api发送http请求   HttpURLConnection
     */
    public static void main(String[] args) {
        String urlStr = "http://www.baidu.com/";

        URL url = null;
        try {
            url = new URL(urlStr);

            // 打开连接
            URLConnection uc = null;
            try {
                uc = url.openConnection();
                // HttpURLConnection是URLConnection的实现之一
                HttpURLConnection huc = (HttpURLConnection) uc;
                /**
                 * 可以设置的属性有很多，如get/post、
                 */
                huc.setRequestMethod("GET"); //请求行方式
                huc.setRequestProperty("Accept-Charset","UTF-8"); //请求头属性
                try {
                    // 获取HttpURLConnection的输入流
                    InputStream httpInStream = huc.getInputStream();
                    // 从流中根据指定编码转为字符串
                    InputStreamReader httpInStreamReader = new InputStreamReader(httpInStream, StandardCharsets.UTF_8);
                    // 一行一行的读取：先指定读取器，后一行一行的读
                    BufferedReader bfReader = new BufferedReader(httpInStreamReader);
                    String line;
                    while ((line = bfReader.readLine()) != null){
                        System.out.println(line);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
