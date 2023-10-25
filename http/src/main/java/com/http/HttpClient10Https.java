package com.http;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class HttpClient10Https {
    /**
     * 通过httpclient请求Https
     *  1. 使用需要认证的密钥配置httpclient
     *  2. 配置httpclient绕过https安全认证
     * @param args
     */
    // 配置httpclient绕过https安全认证
    public static void main(String[] args) {
        Registry<ConnectionSocketFactory> build = null;
        try {
            build = RegistryBuilder.<ConnectionSocketFactory>create()
                    // http使用默认的工厂进行绑定
                    .register("com/http", PlainConnectionSocketFactory.INSTANCE)
                    // https使用自定义的协议工厂进行绑定
                    .register("https", trustHttpsCertificates())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PoolingHttpClientConnectionManager poolClients = new PoolingHttpClientConnectionManager(build);
        HttpClientBuilder httpClientBuilder = HttpClients.custom().setConnectionManager(poolClients);
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build(); // 根据配置的client池创建CloseableHttpClient
        // CloseableHttpClient closeableHttpClient = HttpClients.createDefault();// 默认方式
        String urlStr = "https://www.baidu.com/";
        HttpGet hpcGet = new HttpGet(urlStr);// 构造post请求对象

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

    /**
     * 创建支持安全协议的连接工厂
     * @return
     * @throws Exception
     */
    private static ConnectionSocketFactory trustHttpsCertificates() throws Exception {
        SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
        // 加载信任证书
        sslContextBuilder.loadTrustMaterial(null, new TrustStrategy() {
            // 判断是否信任url
            @Override
            public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                return true;
            }
        });
        // 通过被判断为信任的url，构造出来的sslContext就被信任了
        SSLContext sslContext = sslContextBuilder.build();
        SSLConnectionSocketFactory sslFactory = new SSLConnectionSocketFactory(
                sslContext,
                // 支持的协议
                new String[]{"SSLv2Hello","SSLv3","TLSv1","TLSv1.1","TLSv1.2"},
                null, NoopHostnameVerifier.INSTANCE);
        return sslFactory;
    }


}
