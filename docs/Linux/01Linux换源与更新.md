zsh:1: 没有那个文件或目录: 01Linux/



## Ubuntu 22.04 Linux更换源教程

> 为了更快的下载速度，我们常常需要把Ubuntu自带的国外源换成国内源

更换源步骤如下：
 1、备份源列表

```bash
sudo cp /etc/apt/sources.list /etc/apt/sources.list.bak
```

 2、命令行打开`sources.list`文件

```bash
sudo gedit /etc/apt/sources.list
# 或者
sudo vim /etc/apt/sources.list
```

 将源文件内容全部注释，并添加以下内容任意源

> 阿里云源

```cpp
deb http://mirrors.aliyun.com/ubuntu/ focal main restricted universe multiverse
deb-src http://mirrors.aliyun.com/ubuntu/ focal main restricted universe multiverse

deb http://mirrors.aliyun.com/ubuntu/ focal-security main restricted universe multiverse
deb-src http://mirrors.aliyun.com/ubuntu/ focal-security main restricted universe multiverse

deb http://mirrors.aliyun.com/ubuntu/ focal-updates main restricted universe multiverse
deb-src http://mirrors.aliyun.com/ubuntu/ focal-updates main restricted universe multiverse

deb http://mirrors.aliyun.com/ubuntu/ focal-proposed main restricted universe multiverse
deb-src http://mirrors.aliyun.com/ubuntu/ focal-proposed main restricted universe multiverse

deb http://mirrors.aliyun.com/ubuntu/ focal-backports main restricted universe multiverse
deb-src http://mirrors.aliyun.com/ubuntu/ focal-backports main restricted universe multiverse
```

> 清华源

```cpp
deb http://mirrors.tuna.tsinghua.edu.cn/ubuntu/ focal main restricted
deb http://mirrors.tuna.tsinghua.edu.cn/ubuntu/ focal-updates main restricted
deb http://mirrors.tuna.tsinghua.edu.cn/ubuntu/ focal universe
deb http://mirrors.tuna.tsinghua.edu.cn/ubuntu/ focal-updates universe
deb http://mirrors.tuna.tsinghua.edu.cn/ubuntu/ focal multiverse
deb http://mirrors.tuna.tsinghua.edu.cn/ubuntu/ focal-updates multiverse
deb http://mirrors.tuna.tsinghua.edu.cn/ubuntu/ focal-backports main restricted universe multiverse
deb http://mirrors.tuna.tsinghua.edu.cn/ubuntu/ focal-security main restricted
deb http://mirrors.tuna.tsinghua.edu.cn/ubuntu/ focal-security universe
deb http://mirrors.tuna.tsinghua.edu.cn/ubuntu/ focal-security multiverse
```

 3、保存：`ESC+：wq！`

##  Ubuntu22.04 更新

+ 更新之前可以安装nala包管理工具
```shell
sudo apt install nala
# 后续所有apt命令可改为nala了
# 如sudo nala update
```

> 换源之后就可以快速的更新了

```shell
sudo apt-get update: 升级安装包相关的命令,刷新可安装的软件列表(但是不做任何实际的安装动作)

sudo apt-get upgrade: 进行安装包的更新(软件版本的升级)

sudo apt-get dist-upgrade: 进行系统版本的升级(Ubuntu版本的升级)

sudo do-release-upgrade: Ubuntu官方推荐的系统升级方式,若加参数-d还可以升级到开发版本,但会不稳定

sudo apt-get autoclean: 清理旧版本的软件缓存

sudo apt-get clean: 清理所有软件缓存

sudo apt-get autoremove: 删除系统不再使用的孤立软件
```


 4、更新源

```sql
sudo apt-get update
```

 5、更新软件，然后你就可以感受到更换国内源之后的飞速提升了

```csharp
sudo apt-get dist-upgrade
sudo apt-get upgrade
```

(6、或者如下操作)

```
sudo apt update && sudo apt-get dist-upgrade -y && sudo apt-get upgrade -y
```

