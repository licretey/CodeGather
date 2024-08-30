package com.sourceLearn.a08_2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
/**
 * @Description: scope-单例种注入了非单例类型时的失效问题
 * 解决方式1：@lazy
 * 解决方式2：@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS) 底层也是通过生成代理解决
 * 解决方式3：使用对象工厂ObjectFactory<T>
 * 解决方式3：注入ApplicationContext
 *
 * @Date: 2024/8/19 15:47
 */
@ComponentScan("com.sourceLearn.a08_2")
public class A08Application  {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(A08Application.class);
        Ex e = ctx.getBean(Ex.class);
        System.out.println(e.getF1().getClass());
        // 当添加了@lazy后，虽然代理的对象是同一个，但每次调用代理对象的方法时，会由代理对象创建新的f对象
        System.out.println(e.getF1());
        System.out.println(e.getF1());
        System.out.println(e.getF1());


        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> @Scope(value = \"prototype\", proxyMode = ScopedProxyMode.TARGET_CLASS)");
        System.out.println(e.getF2().getClass());
        // @Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS) 底层也是通过生成代理解决
        System.out.println(e.getF2());
        System.out.println(e.getF2());
        System.out.println(e.getF2());

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>使用对象工厂ObjectFactory");
        System.out.println(e.getF3().getClass());
        // 使用对象工厂ObjectFactory
        System.out.println(e.getF3());
        System.out.println(e.getF3());
        System.out.println(e.getF3());

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>注入ApplicationContext");
        System.out.println(e.getF4().getClass());
        // 注入ApplicationContext
        System.out.println(e.getF4());
        System.out.println(e.getF4());
        System.out.println(e.getF4());
        ctx.close();
    }
}
