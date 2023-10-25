package com.licretey.retry;

import com.licretey.retry.orderservice.OrderService;
import org.springframework.stereotype.Service;


/**
 * 手动重试：实现时同时添加重试逻辑
 * 缺点：
 *      由于没有重试间隔，很可能远程调用的服务还没有从网络异常中恢复，所以有可能接下来的几次调用都会失败
 *      代码侵入式太高，调用方代码不够优雅
 *      项目中远程调用的服务可能有很多，每个都去添加重试会出现大量的重复代码
 */
@Service
public class Retry01HandleOrderServiceImpl implements OrderService {
    @Override
    public void addOrder(){
        int times = 1;
        while (times <= 5) {
            try {
                // addOrder
                // 故意抛异常
                int i = 3 / 0;
            } catch (Exception e ) {
                System.out.println("重试" + times + "次");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                times++;
                if (times > 5) {
                    throw new RuntimeException("不再重试！");
                }
            }
        }
    }
}
