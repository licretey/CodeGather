[TOC]

>
>
>+ final
   >  + 修饰的类无法继承、方法无法重写
>  + 修饰的变量只能被赋值1次
>+ static
>+ abstract类
   >  + `abstract类`不能直接通过new来创建对象
>  + 如果子类不是`abstract类`，子类必须重写`abstract类的 abstract方法`（所以抽象方法的权限修饰不能是private）
>  + `final`不能和`abstract类`同时存在（`final`不能继承，而`abstract类`需要重写）
>+ interface
   >  + 默认全是`public static final 字段`和 `public abstract方法`（所以不能直接new）
>  + jdk8 扩展了`public static 方法` 和 `public default 对象方法`
>  + jdk9 扩展了`private`的`static方法`和`private 对象方法`
>+ 内部类
   >  + static内部类：外部类和内部类的资源可以相互访问，包括`private`修饰的（外部类不是静态的时候，需要先new一次拿到外部类，才可以拿到静态内部类，不是class维度）
>  + 非static内部类：外部类和内部类的资源可以相互访问，包括`private`修饰的
     >    + 要先有外部类对象，再去创建内部类（new两次）
>    + 内部类对象包含一个外部类对象的引用（`外部类.this`）

## 一 流

## 二 函数式

## 三  Optional与方法引用

## 四 注解

## 五 反射与动态代理

### 5.1 JDK反射

+ 特点
    + 被代理类必须实现接口
+ JDK工具类说明

```java
// proxy jdk创建的代理对象，代理对象执行时自动赋值
// method、args 被代理目标类的方法与参数（代理类和被代理类实现了同一接口，一般方法名相同）
InvocationHandler.invoke(Object proxy, Method method, Object[] args)
    
// loader指定了被代理目标类的加载器，
// interfaces指定了代理哪个接口，
// InvocationHandler声明了被代理接口的自定义实现
// Object为返回的动态代理对象
// 实际含义：由loader判断被代理目标类，并且代理目标类实现了接口interfaces的方法；而在InvocationHandler中重载了interfaces的方法，添加增强了操作；最后等待创建的代理对象Object的执行(执行时指定了InvocationHandler.invoke中的proxy、方法和参数)
public static Object newProxyInstance(ClassLoader loader,
                                      Class<?>[] interfaces,
                                      InvocationHandler h)
```



### 5.2 Cglib

## 六 泛型

### 6.1 使用泛型

+ 要求
    + 右边的构造器中的泛型可以不指定，默认等于左边
    + 泛型实际使用时不指定类型参数，会默认使用Object（**基本类型不能作为类型参数**）
    + 泛型不支持向上转型（通过上界、下届处理）

```java
public static void main(String[] args) {
    // 非法1
    // List<Parent> parentList = new ArrayList<Son>();
    // 上界方式：
    // ? extends Parent 表示 Parent或Parent的子类
    List<? extends Parent> pList = new ArrayList<Son>();

    // 非法2
    // List<Parent> parentList = new ArrayList<Son>();
    // 下界方式：
    // ? super Parent 表示 Son或Son的父类
    List<? super Son> parentList = new ArrayList<Parent>();
}
```

+ 一般的使用步骤

    + 1、声明包含泛型的类、接口、方法

    + 2、泛型的基础上操作
    + 3、调用处明确指定泛型

### 6.2 自定义泛型

+ 泛型类、接口：泛型类的派生子类，如果是泛型类，必须与父泛型类保持一致；不是，则需要指定父泛型类的类型

+ 泛型接口

```java
// 1、声明
// 接口或类上定义的泛型，全局可以使用
public interface InterfaceT<T, S> {
    // 2、操作
    T fun1(S s);
}

// 3、使用
public class InterfaceString implements InterfaceT<String, Integer> {
    public String fun1(Integer num) {
        return "指定了泛型的接口实现！";
    }
}
```

+ 泛型方法：定义在访问修饰符和方法返回类型之间 (注意指定泛型：在`.`与`方法名`之间)
+ 泛型方法中类型参数可以与该泛型类中的泛型参数一致，只是形势上一致，实际毫无关系
+ 泛型方法可以声明为static，但泛型类的相关泛型方法不可以

```java
public class Dog{
	// InterfaceString中没有定义泛型了，
    // 需要通过<T,R>重新定义泛型，fun2中使用泛型 
    // 方式上定义的泛型，仅方法中可用
    // 1、声明
    private <T,R> T fun2(R r){
        // 2、操作
        T r1 = (T) r;
        return r1;
    }
    
    // 3、使用
    public static void main(String[] args) {
        Dog dog = new Dog();
        Integer result = dog.<Integer, String>fun2("123");
        // 自动类型识别
        // Integer指定了T, String指定了R
        Integer result2 = ite.fun2("123");
    }
}
   
```

### 6.3 上界、下界

+ 上界与下界不能同时指定
+ 使用上界时，如果由返回，返回的类型是上界类型；新增只可以加null
+ 使用下界时，如果有返回，返回的类型是Object（因为只要是下界的父类就可以指定，而Object是最顶级的父类）

```java
public class Demo{
    class A {}
    class B extends A {}
    class C extends B {}
    class D extends C {}

    public static void main(String[] args) {
        List<? extends  C> aList = new ArrayList<A>(); // 异常
        List<? extends  C> bList = new ArrayList<B>(); // 异常
        List<? extends  C> cList = new ArrayList<C>();

        List<? super B> List1 = new ArrayList<A>();
        List<? super B> List2 = new ArrayList<B>();
        List<? super B> List3 = new ArrayList<C>();    // 异常
    }
}
```



### 6.4 泛型擦除

+ 1.5之后能很好的兼容泛型，是因为泛型信息只存在与代码编译阶段，进入JVM之前，与泛型相关的信息会被擦除，即类型擦除
+ 桥接方法：为了兼容类型擦除，确定了类型的泛型实现中，jdk自动会生成一个Object方法去桥接对应的类型方法

## 七 枚举