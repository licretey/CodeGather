package com.sourceLearn.a04_2;

import com.sourceLearn.a04.Bean1;
import com.sourceLearn.a04.Bean2;
import com.sourceLearn.a04.Bean3;
import com.sourceLearn.a04.Bean5;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.core.MethodParameter;
import org.springframework.core.env.StandardEnvironment;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Description:
 * @Date: 2024/8/8 17:25
 */
public class DigInAutowired {
    public static void main(String[] args) throws Throwable {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //  registerSingleton直接提供bean，不会再执行 创建过程，依赖注入，初始化 操作（原本由beanFactory执行，被跳过）
        beanFactory.registerSingleton("bean2", new Bean2());
        beanFactory.registerSingleton("bean3", new Bean3());

        // 用于解析string等
        beanFactory.setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());
        // 用于对${}进行解析
        beanFactory.addEmbeddedValueResolver(new StandardEnvironment()::resolvePlaceholders);

        // 1. 查找哪些成员变量，方法参数被标记了注解，这些数据称之为InjectionMetadata
        AutowiredAnnotationBeanPostProcessor processor = new AutowiredAnnotationBeanPostProcessor();
        // 后处理器解析Autowired时需要找到被注入的bean，而这个bean是需要由beanFactory提供
        processor.setBeanFactory(beanFactory);

        Bean5 bean5 = new Bean5();
        System.out.println(bean5);
        // 执行依赖注入 @Autowired @Value
        // 参数数2表示被注入的bean，参数3是参数2bean的名称；
        // processor.postProcessProperties(null, bean5, "bean5");
        // Bean2被注入，Bean3没有，因为Bean3使用的是Resource
        // System.out.println(bean5);


        // postProcessProperties实际逻辑如下
        postProcessPropertiesDoing(beanFactory, bean5);
    }

    private static void postProcessPropertiesDoing(DefaultListableBeanFactory beanFactory, Bean5 bean5) throws Throwable {
        Method findAutowiringMetadata = AutowiredAnnotationBeanPostProcessor.class.getDeclaredMethod("findAutowiringMetadata", String.class, Class.class, PropertyValues.class);
        findAutowiringMetadata.setAccessible(true);
        // 获取bean1对象中加了@Autowired @Value的成员变量、方法参数信息
        InjectionMetadata injectionMetadata = (InjectionMetadata) findAutowiringMetadata.invoke("bean5", Bean5.class, null);
        System.out.println(injectionMetadata);

        // 2. 调用InjectionMetadata来进行依赖注入，注入式按类型查找
        injectionMetadata.inject(bean5, "bean5", null);
        System.out.println(bean5);


        // findBeanByType(beanFactory);
    }

    // 如何按类型查找成员变量
    // 先封装要查找的对象信息
    private static void findBeanByType(DefaultListableBeanFactory beanFactory) throws NoSuchFieldException, NoSuchMethodException {
        Field bean3 = Bean1.class.getDeclaredField("bean3");
        DependencyDescriptor dd1 = new DependencyDescriptor(bean3, false);
        // 在beanFactory中查找bean3
        Object o = beanFactory.doResolveDependency(dd1, null, null, null);
        System.out.println(o);

        // 如何按类型查找方法的参数
        // 先封装要查找的对象信息
        Method setBean2 = Bean1.class.getDeclaredMethod("setBean2", String.class);
        DependencyDescriptor dd2 = new DependencyDescriptor(new MethodParameter(setBean2, 0), false);
        // 在beanFactory中查找bean2对象
        Object o2 = beanFactory.doResolveDependency(dd2, null, null, null);
        System.out.println(o2);

        // 如何查找环境变量注入
        Method setHome = Bean1.class.getDeclaredMethod("setHome", String.class);
        DependencyDescriptor dd3 = new DependencyDescriptor(new MethodParameter(setHome, 0), true);
        Object o3 = beanFactory.doResolveDependency(dd3, null, null, null);
        System.out.println(o3);
    }
}
