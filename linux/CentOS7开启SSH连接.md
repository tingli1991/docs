# CentOS7开启SSH连接
#### 1.检查并安装 openssh-server
  首先，要确保CentOS7安装了openssh-server，在终端中输入yum list installed | grep openssh-server 进行检查
![图1](https://github-1251498502.cos.ap-chongqing.myqcloud.com/CentOS7%E5%BC%80%E5%90%AFSSH%E8%BF%9E%E6%8E%A5/openssh-server-001.png)
上图显示已经安装了 openssh-server，如果又没任何输出显示表示没有安装 openssh-server，通过输入yum install openssh-server来进行安装openssh-server，如下图：

![图2](https://github-1251498502.cos.ap-chongqing.myqcloud.com/CentOS7%E5%BC%80%E5%90%AFSSH%E8%BF%9E%E6%8E%A5/20161008123406762.jpg)
#### 2.修改sshd_config配置文件
  找到了 **/etc/ssh/** 目录下的sshd服务配置文件 **sshd_config**，用vi编辑器打开  
  2.1 将文件中，关于监听端口、监听地址前的 #号去除，具体操作步骤如下图：
![图3](https://github-1251498502.cos.ap-chongqing.myqcloud.com/CentOS7%E5%BC%80%E5%90%AFSSH%E8%BF%9E%E6%8E%A5/20161008123807764.jpg)  
  2.2 然后开启允许远程登录   
  ![图4](https://github-1251498502.cos.ap-chongqing.myqcloud.com/CentOS7%E5%BC%80%E5%90%AFSSH%E8%BF%9E%E6%8E%A5/20180807231423.png)  
  2.3 最后，开启使用用户名密码来作为连接验证   
  ![图5](https://github-1251498502.cos.ap-chongqing.myqcloud.com/CentOS7%E5%BC%80%E5%90%AFSSH%E8%BF%9E%E6%8E%A5/20161008124037166.jpg)   
  完成以上步骤后保存并推出vi编辑器
#### 3.开启  sshd  服务，输入 sudo service sshd start   
  ![图5](	https://github-1251498502.cos.ap-chongqing.myqcloud.com/CentOS7%E5%BC%80%E5%90%AFSSH%E8%BF%9E%E6%8E%A5/20180807232954.png)   
  3.1检查  sshd  服务是否已经开启，输入ps -e | grep sshd   
  ![图6](	https://github-1251498502.cos.ap-chongqing.myqcloud.com/CentOS7%E5%BC%80%E5%90%AFSSH%E8%BF%9E%E6%8E%A5/20180807233238.png)   
  或者输入netstat -an | grep 22  检查  22 号端口是否开启监听(如果你的服务器没有netstat指令请自行安装net-tools工具)
  ![图7](https://github-1251498502.cos.ap-chongqing.myqcloud.com/CentOS7%E5%BC%80%E5%90%AFSSH%E8%BF%9E%E6%8E%A5/20161008124408719.jpg)   

#### 常见问题
##### error: kex protocol error: type 30 seq 1 [preauth] 正确处理姿势
在sshd-config文件添加如下代码便可解决  
```
KexAlgorithms curve25519-sha256@libssh.org,ecdh-sha2-nistp256,ecdh-sha2-nistp384,ecdh-sha2-nistp521,diffie-hellman-group14-sha1
```
