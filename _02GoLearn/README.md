[TOC]

## 一

## 二 面向对象

### 2.1 封装抽象
+ 大写表示public
+ 小写表示private
+ `type ClassName stuct{}` 定义基本结构
+ `func (this *ClassName) funcName(args){}` 定义类的方法(不使用指针无法更改对象中的值)

### 2.2 继承
+ 在新结构体中包含一个老结构体即可（无需变量）

### 2.3 多态
+ 使用`interface`定义接口，多个类需要实现该接口的**所有方法**（表示有关系）
+ 后续使用父类指针指向子类对象即可

### 2.4 interface{}
+ 万能类型
+ 通过`arg.(type)`断言判断类型
### 2.5 反射
+ 反射：通过变量找到变量的类型（pair=类型+value）
+ `arg.(type)`能够断言成功，是因为
