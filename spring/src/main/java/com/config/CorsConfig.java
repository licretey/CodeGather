package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域配置
 * 如果有springSecurity，也需要配置
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 允许跨域访问的域名
        registry.addMapping("/**")
                // 允许跨域访问的源头
               .allowedOrigins("*")
                // 允许跨域访问的请求方式
               .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
               // 允许跨域访问的请求头
                .allowedHeaders("*")
                // 允许跨域访问的请求携带cookie
               .allowCredentials(true)
                // 允许跨域访问的时长
               .maxAge(3600);
    }
}
