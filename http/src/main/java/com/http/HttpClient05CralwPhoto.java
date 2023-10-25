
package com.http;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class HttpClient05CralwPhoto {
    /**
     * HttpClient爬取一张图片
     *
     * @param args
     */
    public static void main(String[] args) {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault(); // 可关闭的http客户端
        String urlStr = "http://www.roadjava.com/image/2021/06/37437080254909547_1.png";
        HttpGet hpcGet = new HttpGet(urlStr);// 构造get请求对象
        CloseableHttpResponse response = null; // 可关闭的http响应对象
        try {
            response = closeableHttpClient.execute(hpcGet); //发送请求
            StatusLine statusLine = response.getStatusLine(); // 代表本次请求的成功或失败
            if (HttpStatus.SC_OK == statusLine.getStatusCode()) {
                HttpEntity entity = response.getEntity(); // 获取响应结果: DecompressEntity

                String contentTypeValue= entity.getContentType().getValue();
                String suffix = ".jpg";
                if (contentTypeValue.contains(suffix) || contentTypeValue.contains("jepg")) {
                    suffix = ".jpg";
                } else if (contentTypeValue.contains("bmp") || contentTypeValue.contains("bitmap")){
                    suffix = ".bmp";
                } else if (contentTypeValue.contains("png")){
                    suffix = ".png";
                }else if (contentTypeValue.contains("gif")){
                    suffix = ".gif";
                }
                // 处理二进制文件字节流
                byte[] bytes = EntityUtils.toByteArray(entity);
                String localPath = "D:\\abc"+suffix;
                FileOutputStream fos = new FileOutputStream(localPath);
                fos.write(bytes);
                fos.close();
                EntityUtils.consume(entity);
            } else {
                System.out.println("响应失败，响应码：" + statusLine.getStatusCode());
            }
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
