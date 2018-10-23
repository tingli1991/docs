**zipkin是什么？**  
  他是一个全链路追踪   
  
**参考资料**  
  安装教程：https://blog.csdn.net/qq_20989105/article/details/80430356  
  使用教程：https://blog.csdn.net/zs1041126478/article/details/80161799  
  
**源码地址：** https://github.com/openzipkin/zipkin  
**Centos7源码安装教程：**  
``` BASH
  wget https://codeload.github.com/openzipkin/zipkin/tar.gz/2.11.7
  
  chromd u+x zipkin-2.11.7.tar.gz
  
  tar -zxvf zipkin-2.11.7.tar.gz
  
  mv zipkin-2.11.7 zipkin
  
  cd zipkin
  
  # Build the server and also make its dependencies
  ./mvnw -DskipTests --also-make -pl zipkin-server clean install
  
  # Run the server
  java -jar ./zipkin-server/target/zipkin-server-*exec.jar
```  

**Docker 安装：**
``` Bash
  docker run -d -p 9411:9411 openzipkin/zipkin
  
  firewall-cmd --zone=public --add-port=4411/tcp --permanent  
  
  firewall-cmd --reload
```

**安装java的jdk：**  
``` Bash
  #安装路径
  /usr/local/java/jdk-10.0.2

  vim /etc/profile
  
  export JAVA_HOME=/usr/local/java/jdk-10.0.2
  export JRE_HOME=/usr/local/java/jre-10.0.2
  export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:$JRE_HOME/lib
  export PATH=$JAVA_HOME/bin:$JRE_HOME/bin:$PATH
  
  source /etc/profile
```

**docker 安装mysql**  
``` Bash
  #先安装docket管理界面工具
  docker run -d -p 9000:9000 -v /var/run/docker.sock:/var/run/docker.sock -v portainer_data:/data portainer/portainer
  
  #开放端口
  firewall-cmd --zone=public --add-port=3306/tcp --permanent
  firewall-cmd --reload

  #安装mysql
  docker pull mysql/mysql-server:5.6
  docker run -d -p 3306:3306 --name=mysql -d mysql/mysql-server
  
  #查询默认超级管理员密码并登录mysql
  docker logs mysql 2>&1 | grep GENERATED
  docker exec -it mysql mysql -uroot -p

  #第一次修改密码
  ALTER USER 'root'@'localhost' IDENTIFIED BY 'ChinaNet910111'; 
  use mysql;
  update user host='%' where user='root';
  alter user 'root'@'%' identified with mysql_native_password by 'ChinaNet910111';
```  
