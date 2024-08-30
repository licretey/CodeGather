package com.sourceLearn.a02;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebApplicationContext;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import java.util.Map;

/**
 * @Description: beanFactory不会自动注入factory后处理器、bean后处理器、解析${}等
 * 但是Application会处理上述问题
 * @Date: 2024/8/5 19:24
 */
public class TestBeanFactory {
    public static void main(String[] args) {
        TestBeanFactory.beanFactoryUse();

        // xmlApplicationContext的实现
        // TestBeanFactory.xmlApplicationContextImpl();
        // 基于注解配置的容器实现
        TestBeanFactory.annotationApplicationContextImpl();
        // 基于servet web服务器的容器实现
        TestBeanFactory.servletWebServiceApplicationContextImpl();

    }

    private static void servletWebServiceApplicationContextImpl() {
        AnnotationConfigServletWebApplicationContext annotationConfigServletWebApplicationContext =
            new AnnotationConfigServletWebApplicationContext(WebConfig.class);
        for (String beanDefinitionName : annotationConfigServletWebApplicationContext.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
        System.out.println("---------------------AnnotationConfigServletWebApplicationContext--------------------------------");
    }

    private static void annotationApplicationContextImpl() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        // 不同于xml需要配置<context:annotation-config/>标签，手动注入beanFactory后处理器和bean后处理器，该容器自动注入这些后处理器
        for (String beanDefinitionName : annotationConfigApplicationContext.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName); // 无bean
        }
        System.out.println("---------------------AnnotationConfigApplicationContext--------------------------------");
    }

    private static void xmlApplicationContextImpl() {
        // 读取classpath路径下配置文件
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("");
        // 读取磁盘路径下配置文件(可以使用相对路径，相对于module开始，如'src\\main\\resources\\test.xml'，项目工作目录需要调整为当前模块)
        FileSystemXmlApplicationContext fileSystemXmlApplicationContext = new FileSystemXmlApplicationContext("");

        // 1 这两个容器内存做的事情：（解析配置，初始化bean，交给beanfactory)
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName); // 无bean
        }
        System.out.println("-------------------------DefaultListableBeanFactory----------------------------");
        // 2.1 从xml中读取出配置信息，放到beanFactory中
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        // 2.2 设置读取配置文件的路径
        xmlBeanDefinitionReader.loadBeanDefinitions("configFilePath");
        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName); // 有bean
        }
        System.out.println("------------------loadBeanDefinitions()-----------------------------------");
    }

    private static void beanFactoryUse() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        // 提交bean的定义（class,scope,初始化，销毁），自动注入
        AbstractBeanDefinition definition = BeanDefinitionBuilder.genericBeanDefinition(Config.class).setScope("singleton").getBeanDefinition();
        // 1 beanFactory中有了一个config的bean (1个，未解析@Bean将bean1，bean2注入)
        factory.registerBeanDefinition("config", definition);
        // 通过名字打印存在的bean
        for (String beanDefinitionName : factory.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
        System.out.println("---------------------genericBeanDefinition--------------------------------");

        // 2.1 给beanFactory添加常用后处理器，扩展beanFactory，（同时设置了比较器控制顺序）
        AnnotationConfigUtils.registerAnnotationConfigProcessors(factory);
        // 通过名字打印存在的bean(多个，后处理器bean，未解析@Bean将bean1，bean2注入)
        for (String beanDefinitionName : factory.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
        System.out.println("-----------------------registerAnnotationConfigProcessors------------------------------");

        Map<String, BeanFactoryPostProcessor> factoryOfType = factory.getBeansOfType(BeanFactoryPostProcessor.class);
        // 2.2 执行后处理
        factoryOfType.values().forEach(processor -> {
            processor.postProcessBeanFactory(factory);
        });
        // 通过名字打印存在的bean(后处理解析了@Bean将bean1，bean2注入)
        for (String beanDefinitionName : factory.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }

        System.out.println("-----------------------factoryOfType------------------------------");
        // 能否直接使用bean1，bean2
        Bean2 bean2 = factory.getBean(Bean1.class).getBean2();
        System.out.println(bean2); // bean1构造了，但bean2打印为null，因为Autowired注解扩展没有生效
        System.out.println("-----------------------bean2------------------------------");
        // 3.1 Bean后处理器：对Bean生命周期的各个阶段提供扩展，如@Autowired,@Resource...
        Map<String, BeanPostProcessor> beansOfType = factory.getBeansOfType(BeanPostProcessor.class);
        // 3.2 beanFactory建立Bean后处理器机扩展（beanFactory有这些处理器，但无联系）
        beansOfType
            .values()
            // 设置后处理器的加载顺序
            // .stream().sorted(factory.getDependencyComparator())
            .forEach(factory::addBeanPostProcessor);
        Bean2 bean2New = factory.getBean(Bean1.class).getBean2();
        System.out.println(bean2New); // bean1构造了，但bean2打印为null，因为Autowired注解扩展没有生效
        System.out.println("-----------------------bean2New------------------------------");
        // 单例bean默认懒加载，使用时才会去创建
        // 3.3 可以提前注入单例Bean
        factory.preInstantiateSingletons();
    }


    @Configuration
    static class Config {
        @Bean
        public Bean1 bean1() {
            return new Bean1();
        }

        @Bean
        public Bean2 bean2() {
            return new Bean2();
        }
    }

    @Component
    static class Bean1 {
        private static final Logger log = LoggerFactory.getLogger(Bean1.class);

        public Bean1() {
            log.debug("Build bean1");
        }

        @Autowired
        private Bean2 bean2;

        public Bean2 getBean2() {
            return bean2;
        }
    }

    @Component
    static class Bean2 {
        private static final Logger log = LoggerFactory.getLogger(Bean2.class);

        public Bean2() {
            log.debug("Build bean2");
        }
    }


    // 用于servlet webservice配置
    @Configuration
    static class WebConfig {
        // 必须
        @Bean
        public ServletWebServerFactory servletWebServerFactory() {
            // 会使用内嵌tomcat服务器
            return new TomcatServletWebServerFactory();
        }

        // 必须
        @Bean
        public DispatcherServlet dispatcherServlet() {
            return new DispatcherServlet();
        }

        // 必须
        // DispatcherServlet需要运行在tomcat中
        @Bean
        public DispatcherServletRegistrationBean registrationBean(DispatcherServlet dispatcherServlet) {
            return new DispatcherServletRegistrationBean(dispatcherServlet, "/");
        }

        // 额外添加一个控制器用于测试
        @Bean("/hello")
        public org.springframework.web.servlet.mvc.Controller controller1() {
            return new Controller() {
                @Override
                public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
                    response.getWriter().print("Hello");
                    return null;
                }
            };
        }
    }
}
