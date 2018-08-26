# Docker 学习资料
#### 什么是docker？
**官方概述：** Docker 是世界领先的软件容器平台。开发人员利用 Docker 可以消除协作编码时“在我的机器上可正常工作”的问题。运维人员利用 Docker 可以在隔离容器中并行运行和管理应用，获得更好的计算密度。企业利用 Docker 可以构建敏捷的软件交付管道，以更快的速度、更高的安全性和可靠的信誉为 Linux 和 Windows Server 应用发布新功能。  
**个人认知：**  
* 节约成本和时间，能够实现快速部署和启动  
* 标准化应用发布 
* 方便做持续集成 
* docker是一个非常轻量级的容器，所以使用docker部署出来的集群也是轻量级的 
* 方便构建基于SOA或者微服务的系统 
* 每个应用都有自己独立的运行空间，应用于应用之间相互隔离 

### 关于docker的相关链接   
* 官网： https://www.docker.com/ 
* 官方镜像仓库：https://hub.docker.com/ 
* 官网中文文档：https://docs.docker-cn.com/  

### Docker 图形化管理工具    
* **portainer.io：**  [官网地址](https://portainer.io/) [官网安装教程](https://portainer.io/install.html)  [中文安装教程](http://blog.51cto.com/ganbing/2083051)  

### 基本命令介绍  

|        命令                      |                解析                                          |
|----------------------------------|--------------------------------------------------------------|
| docker --version                 | 查看docker的版本号                                           |
| docker --help                    | 查看docker的帮助文档                                         |
| docker xxx --help                | 查看具体命令的帮助文档(xxx表示子命令)                        |
| docker run xxx                   | 拉去docker镜像(xxx:表示镜像名称，例如redis)                  |
| docker images                    | 查看本地cocker存放的镜像                                     |
| docker ps                        | 查看当前正在运行的容器                                       |
| sudo systemctl start docker      | 启动已经安装好的docker                                       |
| sudo systemctl restart docker    | 重启docker服务                                               |
| sudo systemctl stop docker       | 停止docker服务                                               |  


### 安装 Docker 
Docker的安装其实很简单，官网已经有很成熟的教程，直接按照官方教程一步一步走就可以啦，建议搭建安装docker ce的社区版本(当然啦有钱任性也是可以装ee版的，哈哈)，具体的链接（记得要找准自己的安装环境哟，比如我的就是Centos7那么我肯定就选择Centos7啦）：https://docs.docker-cn.com/engine/installation/  

### Docker的镜像、容器以及仓储的关系 
* **镜像：** 指的就是打包好的应用（镜像通常分为，仓库镜像和本地镜像两种）  
* **仓库：** 存储镜像的地方通常我们把它称之为仓库，例如：http://hub.docker.com 就是docker官方提供的仓库 
* **容器：** 所谓的容器则就是基于镜像所运行出来的环境，我们则把它称之为容器 

### 配置镜像加速器(以阿里为例)  
* **第一步：** 登录 [阿里开发者平台](https://dev.aliyun.com) 
* **第二步：** 接着点击首页右上角的【击管理中心】按钮，进入到管理控制台界面，并在左边的菜单栏找到并点击【镜像加速器】按钮，那么這个时候我们就可以在這个页面申请得到一个阿里给我们分配的加速地址(例如：https://2g2ra1ex.mirror.aliyuncs.com) 
* **第三步：** 拿到加速地址之后就该我们改配置的时候来了(由于系统的不同，操作方式有却别，本文只讲Centos7，而且阿里和docker的文档其实都有)  
```Bash
sudo mkdir -p /etc/docker
sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://2g2ra1ex.mirror.aliyuncs.com"]
}
EOF
sudo systemctl daemon-reload
sudo systemctl restart docker
```

### 构建单机多容器的运行环境（docker命令：docker run -d -p --name image） 
![环境示意图](https://github-1251498502.cos.ap-chongqing.myqcloud.com/Docker/20180826184415.png)  
![集群管理后台显示](https://github-1251498502.cos.ap-chongqing.myqcloud.com/Docker/20180826212023.png)  







