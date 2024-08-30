package com.sourceLearn.a03_2;

import java.util.ArrayList;
import java.util.List;


// spring设计模式：模板方法
public class TestMethodTemplate {
    public static void main(String[] args) {
        // MyBeanFactory myBeanFactory = new MyBeanFactory();
        MyBeanFactory2 myBeanFactory = new MyBeanFactory2();
        myBeanFactory.addBeanPostProcessor(new BeanPostProcessor() {

            @Override
            public void inject() {
                System.out.println("解析@Autowired");
            }
        });
        myBeanFactory.addBeanPostProcessor(new BeanPostProcessor() {
                @Override
                public void inject() {
                    System.out.println("解析@Resource");
                }
            }
        );
        myBeanFactory.getBean();
    }


    /**
     * beanFactory简单模拟
     * 写法缺点：扩展性不强
     */
    static class MyBeanFactory {
        public Object getBean() {
            Object bean = new Object();
            System.out.println("构造" + bean);
            System.out.println("依赖注入" + bean);
            System.out.println("初始化" + bean);
            return bean;
        }
    }


    // 优化：模板方法
    // 1.定义接口：不确定操作的封装，可以有多个
    static interface BeanPostProcessor {
        // 对依赖注入阶段的扩展
        public void inject();
    }

    static class MyBeanFactory2 {
        List<BeanPostProcessor> processors = new ArrayList<>();

        public Object getBean() {
            Object bean = new Object();
            System.out.println("构造" + bean);
            System.out.println("依赖注入" + bean);
            // 执行扩展
            for (BeanPostProcessor processor : processors) {
                processor.inject();
            }
            System.out.println("初始化" + bean);
            return bean;
        }

        public void addBeanPostProcessor(BeanPostProcessor postProcessor) {
            processors.add(postProcessor);
        }
    }
}