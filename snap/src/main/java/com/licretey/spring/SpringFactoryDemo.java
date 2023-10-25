package com.licretey.spring;

import java.io.InputStream;
import java.util.*;

public class SpringFactoryDemo {
    // 读取配置文件信息的对象
    private static Properties props;

    // 容器，用于单例时存储new出来的对象
    private static Map<String, Object> beans;

    static {
        props = new Properties();
        /**
         * 获取properties文件的流对象
         * 注意：不要使用FileInputStraem, 因为web项目部署后src的相对路径是不确定的，自然无法找到配置文件
         *
         */
        try {
            // 获取配置文件流（参数：resource目录下的配置文件名）
            InputStream in = SpringFactoryDemo.class.getClassLoader().getResourceAsStream("bean.properties");
            props.load(in);

            // 实例化容器。自动创建所有配置文件中的对象存入容器中
            beans = new HashMap<>();
            Enumeration<Object> keys = props.keys(); // 读取配置文件中所有的key
            while (keys.hasMoreElements()) {
                String key = keys.nextElement().toString();
                String beanPath = (String) props.get(key);
                Object valueBean = Class.forName(beanPath).newInstance();
                beans.put(key, valueBean);
            }
        } catch (Exception e) {
            throw new ExceptionInInitializerError("初始化properties配置文件异常！");
        }
    }

    /**
     * 根据bean的配置名称获取全限定类名后获取对象
     *
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
        Object bean = null;
        try {
            String beanPath = props.getProperty(beanName);
            // 根据全限定类名反射创建对象
            bean = Class.forName(beanName).newInstance();
        } catch (Exception e) {
            e.printStackTrace(); // 开发中禁用此方法，容易造成内存溢出
        }
        return bean;
    }

    /**
     * 根据bean的名字单例方式获取对象
     *
     * @param beanName
     * @return
     */
    public static Object getSingleBean(String beanName) {
        return beans.get(beanName);
    }


}

