#### 安装部署
##### 1.安装yum-utils （如有可省略）
``` Bash
yum install -y yum-utils
```
##### 2.设置 docker yum 源
```Bash
yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
```
##### 3.版本预览&安装
```Bash
yum list docker-ce --showduplicates | sort -r
yum install -y docker-ce-18.06.3.ce-3.el7
```
##### 4.启动&设置开机启动
```Bash
systemctl start docker     启动Docker 
systemctl enable docker    开机自启动
systemctl daemon-reload    重启守护进程
systemctl restart docker   重启docker
```
##### 5.查看docker 版本
```Bash
docker version
```
##### 6.开启远程端口实现远程api调用
```Bash
vim /lib/systemd/system/docker.service

//修改如下参数：
ExecStart=/usr/bin/dockerd -H tcp://0.0.0.0:2375 -H unix:///var/run/docker.sock

//查看远程镜像
docker -H tcp://192.168.3.10:2375 images
```
##### 7.打开远程访问端口
```Bash
firewall-cmd --zone=public --add-port=2375/tcp --permanent
firewall-cmd --reload
firewall-cmd --zone=public --query-port=2375/tcp
```



