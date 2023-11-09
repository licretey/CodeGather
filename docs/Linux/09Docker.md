## 配置阿里云镜像

![image-20230702135527243](.\images\image-20230702135527243.png)

```shell
# 可以通过修改daemoni配置文件/etc/docker/daemon.json来使用加i速器
sudo mkdir -p /etc/docker

sudo tee /etc/docker/daemon.json <<-'EOF'
{
	"registry-mirrors":["https://v9j5rufo.mirror.aliyuncs.com"]
}
EOF

sudo systemctl daemon-reload
sudo systemctl restart docker
```

