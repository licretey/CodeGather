package com.licretey.retry;

import com.licretey.retry.annotation.MyRetryable;
import com.licretey.retry.orderservice.OrderService;

public class Retry05AopOrderServiceImpl implements OrderService {

    @Override
    @MyRetryable(retryTimes = 5, retryInterval = 2)
    public void addOrder() {
        int i = 3/0;
        // addOrder
    }
}
