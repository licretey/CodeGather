+ AutowiredAnnotationBeanPostProcessor

```java
processor.postProcessProperties(null, bean1, "bean1");
// ===》方法实现如下
@Override
public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) {
    // 查找添加对应注解的参数变量等，封装为InjectionMetadata
    InjectionMetadata metadata = findAutowiringMetadata(beanName, bean.getClass(), pvs);
    try {
        // 反射给属性赋值
        metadata.inject(bean, beanName, pvs);
    }
    catch (BeanCreationException ex) {
        throw ex;
    }
    catch (Throwable ex) {
        throw new BeanCreationException(beanName, "Injection of autowired dependencies failed", ex);
    }
    return pvs;
}
```