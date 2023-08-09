package com.licretey.retry.orderservice;

import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{
    @Override
    public void addOrder() {
        System.out.println("添加Order实现！！！");
    }
}
