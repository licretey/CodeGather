package com.licretey.retry;

import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * JDK动态代理：
 *      实现InvocationHandler代理接口，在invoke方法中添加重试
 *      提供静态的对外接口，返回代理对象
 * 缺点：
 *      代理的类，没有实现任何接口，那么就无法为其创建代理对象
 */
@Service
public class Retry03JdkProxyOrderServiceImpl implements InvocationHandler {

    private final Object subject;

    public Retry03JdkProxyOrderServiceImpl(Object subject) {
        // 构造被代理对象的代理类
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        int times = 1;
        while (times <= 5) {
            try {
                // 故意抛异常
                int i = 3 / 0;
                return method.invoke(subject, args);
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
     * 对外接口
     * 返回增强后的代理对象
     * @param realSubject
     * @return
     */
    public static Object getProxy(Object realSubject) {
        Retry03JdkProxyOrderServiceImpl handler = new Retry03JdkProxyOrderServiceImpl(realSubject);
        return Proxy.newProxyInstance(handler.getClass().getClassLoader(), realSubject.getClass().getInterfaces(), handler);
    }
}
