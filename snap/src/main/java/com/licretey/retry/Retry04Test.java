package com.licretey.retry;

import com.licretey.retry.orderservice.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/order")
public class Retry04Test {
    @Qualifier("orderServiceImpl")
    @Autowired
    private OrderService orderService;

    @Autowired
    private Retry04CglibProxyOrderServiceImpl cglibProxyOrderService;

    @GetMapping("/addOrder")
    public String addOrder() {
        OrderService orderServiceProxy = (OrderService) cglibProxyOrderService.getCglibProxy(orderService);
        orderServiceProxy.addOrder();
        return "addOrder";
    }
}