package com.sourceLearn.a08_2;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Date: 2024/8/19 15:48
 */
@Component
public class Ex {
    // 解决方式1：@lazy
    // 解决方式1：@Lookup
    @Autowired
    private F1 f1;

    // 解决方式2：@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
    @Autowired
    private F2 f2;

    // 解决方式3：使用对象工厂
    @Autowired
    private ObjectFactory<F3> f3;

    // 解决方式4：注入ApplicationContext
    @Autowired
    private ApplicationContext ctx;

    public F1 getF1() {
        return f1;
    }

    public F2 getF2() {
        return f2;
    }

    public F3 getF3() {
        return f3.getObject();
    }

    public F4 getF4() {
        return ctx.getBean(F4.class);
    }
}
