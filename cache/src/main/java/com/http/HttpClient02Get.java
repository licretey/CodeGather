package com.http;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class HttpClient02Get {
    /**
     * HttpClient发送get无参、有请求头请求
     *
     * @param args
     */
    public static void main(String[] args) {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault(); // 可关闭的http客户端
        String urlStr = "http://www.baidu.com/";
        HttpGet hpcGet = new HttpGet(urlStr);// 构造get请求对象
        // 设置请求头，如设置user-agent
        hpcGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36 Edg/109.0.1518.78");
        // 防盗链：链接必须是该网站的，不可随意设置
        hpcGet.setHeader("Referer",urlStr);
        CloseableHttpResponse response = null; // 可关闭的http响应对象
        try {
            response = closeableHttpClient.execute(hpcGet); //发送请求
            HttpEntity entity = response.getEntity(); // 获取响应结果: DecompressEntity
            // HttpEntity不仅可以作为结果，也可以作为请求的参数实体，有很多实现
            // 对httpEntity操作的工具类
            String responseStr = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            System.out.println(responseStr);
            // 确保流关闭
            EntityUtils.consume(entity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (closeableHttpClient != null) {
                try {
                    closeableHttpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
