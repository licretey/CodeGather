#### 通用注解



##### 1.1 用于创建的注解：功能类似xml中的bean标签

+ @Component(value="beanId")，value值不写时默认值是当前类名且首字母小写

> 以下三个注解的作用和属性同@Component完全一样，是Spring提供给三层架构，让三层对象更清晰

+ @Controller：用于表现层
+ @Service：用于业务层
+ @Repository：用于持久层

##### 1.2 用于注入数据的注解：类似xml中bean标签下的标签

+ @Autowired：自动按照类型注入，位置可以是成员变量、方法

> （只要容器中有唯一的bean对象类型可以和要注入的变量的类型匹配就可；有多个时先按类型匹配出部分对象，然后通过变量名作为名称在结果中查找对应的对象）

+ @Qualifier(value="beanId")：按照类型注入的基础上，再按照名称注入（不能单独使用，需要配合@Autowired）
+ @Resource(name="beanId")：按照bean的id注入，可单独使用

> 以上三个注解都无法注入基本类型和String类型，**集合类型的注入只能通过xml方式**

+ @Value(value="value")：用于注入基本类型和String类型，value属性的值支持Spring的**el表达（SpEl表达式：${表达式}）**

##### 1.3 用于改变作用范围的注解：类似xml中bean便签的scope属性

+ @Scope(value="singleton")：指定bean的作用范围，常用value值singleton、prototype

##### 1.4 和生命周期相关的注解（了解）：类似xml中bean便签的init-method和destory-method属性

+ @PreDestory：指定销毁方法
+ @PostConstruct：指定初始化方法

##### 配置容器注解

+ @Configuration: 表明当前类是一个配置类 ( 当配置类作为AnnocatonConfigApplicationContext的参数创建容器对象时，可以不写 )
+ @ComponentScan: 指定创建容器时要扫描的包(value和basePackages作用一样，都用于指定待扫描路径，可以多个)
+ @Bean:          将方法返回值 作为bean对象注入到容器中(name指定bean的id，默认当前方法名)；被注解方法如果有参数，默认会到容器中查找（方式同Autowired一样，即通过类型去匹配）
+ @Import:  导入其它配置类
+ @PropertySource: 指定Property配置文件的名称和路径（关键字classpath表示是类路径下）

### SpringMVC注解

### MyBatis注解

##### CRUD注解：

+ @Select 
+ @Insert 
+ @Update 
+ @Delete

##### 开启二级缓存

+ @CacheNamespace(blocking="true")

  

## [常用注解](https://blog.csdn.net/qq_45139489/article/details/98946235)

| 限制                      | 说明                                                         |      |
| ------------------------- | ------------------------------------------------------------ | ---- |
| @Null                     | 限制只能为null                                               |      |
| @NotNull                  | 限制必须不为null                                             |      |
| @AssertFalse              | 限制必须为false                                              |      |
| @AssertTrue               | 限制必须为true                                               |      |
| @DecimalMax(value)        | 限制必须为一个不大于指定值的数字                             |      |
| @DecimalMin(value)        | 限制必须为一个不小于指定值的数字                             |      |
| @Digits(integer,fraction) | 限制必须为一个小数，且整数部分的位数不能超过integer，小数部分的位数不能超过fraction |      |
| @Future                   | 限制必须是一个将来的日期                                     |      |
| @Max(value)               | 限制必须为一个不大于指定值的数字                             |      |
| @Min(value)               | 限制必须为一个不小于指定值的数字                             |      |
| @Past                     | 限制必须是一个过去的日期                                     |      |
| @Pattern(value)           | 限制必须符合指定的正则表达式                                 |      |
| @Size(max,min)            | 限制字符长度必须在min到max之间                               |      |
| @Past                     | 验证注解的元素值（日期类型）比当前时间早                     |      |
| @NotEmpty                 | 验证注解的元素值不为null且不为空（字符串长度不为0、集合大小不为0） |      |
| @NotBlank                 | 验证注解的元素值不为空（不为null、去除首位空格后长度为0），不同于@NotEmpty，@NotBlank只应用于字符串且在比较时会去除字符串的空格 |      |
| @Email                    | 验证注解的元素值是Email，也可以通过正则表达式和flag指定自定义的email格式 |      |



