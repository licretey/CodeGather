package com.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class HttpClient06Proxy {
    /**
     * HttpClient设置代理、添加连接超时限制和读取超时限制
     *
     * @param args
     */
    public static void main(String[] args) {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault(); // 可关闭的http客户端
        String urlStr = "http://www.github.com/";
        HttpGet hpcGet = new HttpGet(urlStr);
        // 创建一个代理对象 HttpHost类
        String ip = "106.14.255.124";
        int port = 80;
        HttpHost proxy = new HttpHost(ip, port);
        // 对每一个请求进行配置，会覆盖全局的默认配置
        RequestConfig requestConfig = RequestConfig.custom()
                .setProxy(proxy)
                // 设置连接超时，单位ms，即完成tcp3次握手的时间上限
                .setConnectTimeout(5000)
                // 设置读取超时限制，单位ms,即从请求网址处获得响应数据的时间限制
                .setSocketTimeout(300)
                // 指从连接池里获取connection的超时时间（连接池中的连接都被占用了，此时要等待的限制）
                .setConnectionRequestTimeout(5000)
                .build();
        hpcGet.setConfig(requestConfig);
        CloseableHttpResponse response = null;
        try {
            response = closeableHttpClient.execute(hpcGet); //发送请求
            HttpEntity entity = response.getEntity(); // 获取响应结果
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
