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
##### 登录Nacos平台
###### 登录地址：http://服务器IP地址:8848/nacos/  
###### 初始账户&密码：nacos


##### 常见的踩坑 
###### 使用命令sh startup.sh -m standalone启动报错：
  1.错误详情：
``` Bash
/usr/local/java/jdk1.8.0_191//bin/java  -Xms512m -Xmx512m -Xmn256m -Dnacos.standalone=true 
-Djava.ext.dirs=/usr/local/java/jdk1.8.0_191//jre/lib/ext:/usr/local/java/jdk1.8.0_191//lib/ext:/usr/local/nacos/plugins/cmdb:/usr/local/nacos/plugins/mysql 
-Xloggc:/usr/local/nacos/logs/nacos_gc.log -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 
-XX:GCLogFileSize=100M -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8000 -Dnacos.home=/usr/local/nacos 
-Dloader.path=/usr/local/nacos/plugins/health -jar /usr/local/nacos/target/nacos-server.jar  --spring.config.location=classpath:/,classpath:/config/,file:./,file:./config/,file:/usr/local/nacos/conf/ 
--logging.config=/usr/local/nacos/conf/nacos-logback.xml --server.max-http-header-size=524288
nacos is starting with standalone
nacos is starting，you can check the /usr/local/nacos/logs/start.out
```
  2.解决办法(使用如下命令执行)  
``` Bash
bash -f ./startup.sh -m standalone
```
