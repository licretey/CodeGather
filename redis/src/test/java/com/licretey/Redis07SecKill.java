package com.licretey;


import com.licretey.dto.Order;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.*;

@Slf4j
public class Redis07SecKill {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 静态加载指定路径下的lua脚本，并初始化设置
     */
    private static final DefaultRedisScript<Long> SECKILL_SCRIPT;
    static {
        SECKILL_SCRIPT = new DefaultRedisScript<>();
        SECKILL_SCRIPT.setLocation(new ClassPathResource("SecKill.lua"));
        SECKILL_SCRIPT.setResultType(Long.class);
    }

    @Resource
    private RedissonClient redissonClient;

    // 阻塞队列，有元素时才会被唤醒
    private BlockingQueue<Order> orderQueue = new ArrayBlockingQueue<>(1024*1024);

    private static Order proxy;




    @Test
    public String testSecKill() throws InterruptedException {
        Long userId = 112233L;
        String orderId = UUID.randomUUID().toString().replaceAll("-", "");
        // 执行脚本
        Long result = stringRedisTemplate.execute(
                SECKILL_SCRIPT,
                Collections.emptyList(),
                orderId, userId.toString()
        );
        // 不为0，有异常
        if(0 != result.intValue()){
            return 1 == result.intValue() ? "库存不足" : "不能重复下单";
        }
        // 0时有购买资格，创建订单 放入阻塞队列
        Order order = new Order();
        order.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        order.setOrderId(orderId);
        order.setUserId(userId.toString());
        orderQueue.add(order);
        // 获取aop代理的对象
        proxy = (Order) AopContext.currentProxy();

        return "下单成功";
    }


    private final static ExecutorService SECKILL_ORDER_EXECUTOR = Executors.newSingleThreadExecutor();
    @PostConstruct // spring注解，初始化完成后执行
    private void init(){
        SECKILL_ORDER_EXECUTOR.submit(new OrderHandler());
    }

    private class OrderHandler implements Runnable{

        @Override
        public void run() {
            while (true){
                // 获取队列中的订单信息
                try {
                    // while不会造成cpu压力过高，因为orderQueue无元素时会被阻塞
                    Order take = orderQueue.take();
                    // 创建真正的订单
                    handleOrder(take);
                } catch (InterruptedException e) {
                    log.error("处理订单异常：", e);
                }
            }
        }

    }


    /**
     * 创建订单
     * @param take
     */
    private void handleOrder(Order take) {
        String userId = take.getUserId();
        // 防止redis挂了
        RLock lock = redissonClient.getLock("lock:order" + userId);
        boolean isLock = lock.tryLock();
        if(!isLock){
            log.error("不允许重复西单");
            return;
        }

        try {
            // 下单
            proxy.createOrder(take);
            return;
        }finally {
            lock.unlock();
        }
    }
}
