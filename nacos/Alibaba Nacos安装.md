##### 什么是Nacos？
Nacos是一个易于构建云原生应用的动态服务发现、配置管理和服务管理平台，它提供了一组简单易用的特性集，帮助我们快速实现动态服务发现、服务配置、服务元数据及流量管理； 
###### Nacos有下面的关键特性  
  1.服务发现和服务健康监测;  
  2.动态配置服务;   
  3.动态 DNS 服务;  
  4.服务及其元数据管理;  

##### 安装Nacos
###### Nacos的下载地址(这里使用gz的格式安装)   
  [nacos-server-1.1.4.tar.gz](https://github.com/alibaba/nacos/releases/download/1.1.4/nacos-server-1.1.4.tar.gz)  
  [nacos-server-1.1.4.zip](https://github.com/alibaba/nacos/releases/download/1.1.4/nacos-server-1.1.4.zip)  
###### 解压命令：  
``` Bash
tar -zxvf nacos.tar.gz
```
###### 进入bin目录然后启动一个单机版(运行效果如下图所示)
``` Bash
sh startup.sh -m standalone
```
![](https://upload-images.jianshu.io/upload_images/12893700-716d254f4719b26e.png?imageMogr2/auto-orient/strip|imageView2/2/w/1030/format/webp)

##### 执行如下的Linux命令来打开Nacos监听端口的防火墙
``` Bash
firewall-cmd --zone=public --add-port=8848/tcp --permanent 
firewall-cmd --reload
```
