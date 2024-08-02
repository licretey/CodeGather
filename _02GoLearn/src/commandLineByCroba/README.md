[TOC]

> 基于croba构架命令行客户端
> 

+ 命令行格式
+ 1 命令：代表动作
+ 2 参数：代表事物
+ 3 标志：代表动作的修饰
  + 本地标志：只能当前命令使用
  + 持久化标志：当前命令和所有下级命令都可以使用
  + 全局标志：根命令的持久化标志，所有子命令都可以使用


## 一 安装croba


go get github.com/spf13/cobra@latest 

go get github.com/chenquan/viper