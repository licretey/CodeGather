package com.licretey.retry;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * CGLib动态代理：
 *      实现Spring提供的MethodInterceptor代理接口，在intercept方法中添加重试
 *      调用时注入组件，调用对外接口，返回代理对象
 * 缺点：
 *      要对原来的逻辑进行侵入式修改，在每个被代理实例被调用的地方都需要进行调整，这样仍然会对原有代码带来较多修改
 *      如：OrderService orderServiceProxy = (OrderService) cglibProxyOrderService.getCglibProxy(orderService);
 */
@Service
public class Retry04CglibProxyOrderServiceImpl implements MethodInterceptor {

    private Object target;

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) {
        int times = 1;
        while (times <= 5) {
            try {
                // 故意抛异常
                int i = 3 / 0;
                return method.invoke(target, args);
            } catch (Exception e) {
                System.out.println("重试【" + times + "】次");
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
        return null;
    }


    /**
     *  对外接口
     *  返回增强后的代理对象
     * @param objectTarget
     * @return
     */
    public Object getCglibProxy(Object objectTarget){
        this.target = objectTarget;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(objectTarget.getClass());
        enhancer.setCallback(this);
        Object result = enhancer.create();
        return result;
    }


}
