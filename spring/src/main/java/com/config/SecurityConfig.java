// package com.config;
//
// import com.example.filter.JwtAuthenticationTokenFilter;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
// /**
//  * springSecurity配置类
//  * 需要导包
//  */
// @Configuration
// @EnableGlobalMethodSecurity(prePostEnabled = true) // 开启方法级别权限控制
// public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//
//     @Bean
//     public PasswordEncoder passwordEncoder(){
//         return new BCryptPasswordEncoder();
//     }
//
//
//     @Autowired
//     JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
//
//     /**
//      * 前后端分离中，放行登录请求
//      * @param http
//      * @throws Exception
//      */
//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http
//                 //关闭csrf
//                 .csrf().disable()
//                 //不通过Session获取SecurityContext
//                 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                 .and()
//                 .authorizeRequests()
//                 // 对于登录接口 允许匿名访问
//                 .antMatchers("/user/login").anonymous()
//                 // 除上面外的所有请求全部需要鉴权认证
//                 .anyRequest().authenticated();
//
//         //把token校验过滤器添加到过滤器链中
//         http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
//
//         // 添加自定义认证异常类
//         // 添加自定义授权异常类
//         // 配置允许跨域
//         http.cors();
//     }
//
//
//     /**
//      * 注入AuthenticationManager，登录时使用authenticate方法校验用户名密码
//      * @return
//      * @throws Exception
//      */
//     @Bean
//     @Override
//     public AuthenticationManager authenticationManagerBean() throws Exception {
//         return super.authenticationManagerBean();
//     }
// }