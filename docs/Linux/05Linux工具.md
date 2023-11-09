[TOC]

### 基础

+ git
+ vim
+ nvim
+ ranger
+ tree
+ htop

### 查找文件

+ fd
+ fzf

### 文件管理器

+ fd
+ lf

### 窗口管理器

+ i3

### 终端多任务

+ tmux
+ zellij

### zsh

+ oh-my-zsh

  + zsh-autosuggestions
  + zsh-syntax-highlighting
  + incr

  ```shell
   mkdir ~/.oh-my-zsh/plugins/incr 
   # 官网下载文件放置incr目录下
   cd ~/.oh-my-zsh/plugins/incr
   wget https://mimosa-pudica.net/src/incr-0.2.zsh  
   source incr*.zsh
   # 编辑.zshrc文件中的插件配置后重载配置文件即可
   # !!!
   # 如提示incr不存在可将incr-0.2.zsh 改为incr.plugin.zsh  或incr.zsh重试  
  ```

### fish

```shell
# 安装
sudo apt install fish
# 关闭问候语
# 配置文件中 .config/fish/config.fish 中添加如下配置
set fish_greeting
```

+ fish配置环境变量

> 在/etc/fish/conf.d目录下的一个fish文件对应一个环境变量，如PATH.fish内容如下：
>
> set -gx PATH $PATH $JAVA_HOME/bin $JRE_HOME/bin $ROCKETMQ_HOME/bin $ZOOKEEPER_HOME/bin
> 
> 对应profie如下：
>
> export PATH=$PATH:$JAVA_HOME/bin:$JRE_HOME/bin:$ROCKETMQ_HOME/bin:$ZOOKEEPER_HOME/bin



