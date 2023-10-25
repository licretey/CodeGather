package com.licretey.retry;

import com.licretey.retry.orderservice.OrderService;
import com.licretey.retry.orderservice.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 静态代理：对实现增强，外层添加重试
 * 缺点：
 *      如果依赖的服务很多的时候，要为每个服务都创建一个代理类，显然过于麻烦
 *      其次，重试的逻辑都大同小异，仅重试的次数和延时不一样。如果每个类都写这么一长串类似的代码，显然不优雅
 */
@Service
public class Retry02StaticProxyOrderServiceImpl implements OrderService {
    @Autowired
    private OrderServiceImpl orderService;

    @Override
    public void addOrder() {
        int times = 1;
        while (times <= 5) {
            try {
                // 故意抛异常
                int i = 3 / 0;
                orderService.addOrder();
            } catch (Exception e) {
                System.out.println("重试" + times + "次");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                times++;
                if (times > 5) {
                    throw new RuntimeException("不再重试！");
                }
            }
        }

    }
}
