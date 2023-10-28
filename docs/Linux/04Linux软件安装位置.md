[TOC]

+ [软件安装基础知识](https://blog.csdn.net/Courage_Insight/article/details/41827167)

## 安装后的软件位置

1.下载的软件存放位置

`/var/cache/apt/archives`

 2.安装后软件默认位置

`/usr/share`

 3.可执行文件位置 

`/usr/bin`

 4.配置文件位置

`/etc`

 5.`lib`文件位置

`/usr/lib`

## 安装后配置app快捷访问图标

`/usr/share/applications/`

## 桌面图标配置

+ idea.desktop内容如下

```markdown
[Desktop Entry]
Name=Intellij IDEA
Comment=Intellij IDEA
Exec=/home/nico/soft/java/idea-IU-223.8214.52/bin/idea.sh
Icon=/home/nico/soft/java/idea-IU-223.8214.52/bin/idea.png
Terminal=false
Type=Application
Categories=Developer
```

## 蓝牙Bug修复

```
sudo apt install bluetooth
# 安装蓝牙管理工具blueman
sudo apt install blueman -y
```

## [配置systemctl启动1](https://cloud.tencent.com/developer/article/1975011)

## [配置systemctl启动2](https://blog.csdn.net/t624124600/article/details/111085234)

+ 先在`/usr/lib/systemd/system/`下创建unit服务，


## Redis安装

+ 直接安装

```shell
sudo apt install redis-server
-- 安装完成后，测试Redis是否正常工作
redis-cli --version
```

## Zookper安装

+ 下载二进制文件压缩包后解压，执行时要进入到bin目录下用bash shell执行

+ [可能配置文件未创建成功](https://blog.csdn.net/han_xuefeng/article/details/119885832)

## RocketMQ安装

+ [注意配置](https://blog.csdn.net/mario08/article/details/107243730)


## 背景图片位置

+ `user/share/backgrounds`


## PostgreSQL

+ 安装：
+ 初始密码

```shell
# 登录之后给默认用户“postgres”设置密码
sudo -u postgres psql postgres
# 给postgres用户设置密码
postgres= # \password postgres          
# 输入两次密码
Enter new password:
Enter it again:
```
