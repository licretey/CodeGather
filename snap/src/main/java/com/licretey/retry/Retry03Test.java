package com.licretey.retry;

import com.licretey.retry.orderservice.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;



@RestController
@RequestMapping("/order")
public class Retry03Test {
    @Qualifier("orderServiceImpl")
    @Autowired
    private OrderService orderService;

    @GetMapping("/addOrder")
    public String addOrder() {
        OrderService orderServiceProxy = (OrderService)Retry03JdkProxyOrderServiceImpl.getProxy(orderService);
        orderServiceProxy.addOrder(); // 通过代理类执行进行了增强
        return "addOrder";
    }
}