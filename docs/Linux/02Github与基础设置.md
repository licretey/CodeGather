+ 设置Ubuntu的Root密码

```shell
sudo passwd root
-- 输入三次密码即可
```



+ Ubuntu可以使用gh官方token管理工具进行处理

```shell
sudo apt install gh
gh auth login    
```

+ ubuntu22 Dock栏最小化最大化切换

```shell
gsettings set org.gnome.shell.extensions.dash-to-dock click-action 'minimize'
```

+ 安装编解码器，和微软字体（播放流媒体需要）

```shell
 sudo apt install ubuntu-restricted-extras
```

+ 设置gnome-terminal的默认开启位置
> 先安装gconf-editor , 安装完成后打开gconf-editor
> 找到`/desktop/gnome/applications/terminal/exec`
> 并修改该项的值为： gnome-terminal  --geometry 120x30+154+70
> 说明：90：宽度 46：高度 154：横坐标 70：纵坐标


+ terminator安装

> 默认情况下安装完terminator后会替换默认终端，使用如下方式切会自带终端：
> `sudo update-alternatives --config x-terminal-emulator`
> 选择/usr/bin/gnome-terminal.wrapper；