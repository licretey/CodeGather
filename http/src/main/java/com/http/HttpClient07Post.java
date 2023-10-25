package com.http;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class HttpClient07Post {
    /**
     * MIME type : 多用途互联网邮件扩展协议
     * 组成：
     *  MIME-Version 版本
     *  Content-Type 内容类型
     *  Content-transfer-Encoding 编码格式
     *  Content-disposition 内容排列方式
     * 表单form组件的enctype常用的mime类型（content-type类型）
     *  1. application/x-www-form-urlencoded 默认
     *  2. multipart/form-data 上传文件
     *  3. text/plain 普通字符串
     * @param args
     */
    // httpClient post请求
    public static void main(String[] args) {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault(); // 可关闭的http客户端
        String urlStr = "http://www.baidu.com/";
        HttpPost hpcPost = new HttpPost(urlStr);// 构造post请求对象
        // 手动设置请求头
        hpcPost.addHeader("Content-Type","application/x-www-form-urlencoded; charset=utf-8");
        // 设置post参数
        List<NameValuePair> list = new ArrayList<>(); //NameValuePair：接收form中的键值对等信息
        list.add(new BasicNameValuePair("userName","licretey"));
        list.add(new BasicNameValuePair("passwd","123555"));
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(list,StandardCharsets.UTF_8);
        hpcPost.setEntity(formEntity);
        CloseableHttpResponse response = null; // 可关闭的http响应对象
        try {
            response = closeableHttpClient.execute(hpcPost); //发送请求
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
