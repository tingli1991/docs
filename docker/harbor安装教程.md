### harbor安装教程
##### 操作系统、应用及版本信息
* Linux：CentOS 7.6
* Docker : 18.06.3-ce
* docker-compose version:1.25.4
* Harbor：v1.10.1

##### 安装docker-compose
Harbor是通过docker-compose来管理镜像的;  
所以在Harbor主机安装docker-compose是必须的首要的一步;
``` bash
curl -L https://github.com/docker/compose/releases/download/1.25.4/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose 
chmod +x /usr/local/bin/docker-compose
docker-compose --version
```

##### Harbor的域名
如果没有域名的话，可以自己定义一个域名，并在Harbor主机和Docker主机通过向/etc/hosts文件添加条目完成自定义域名与Harbor主机IP的映射关系。
本文中自定义的域名是harbor.litingli.com配置如下：
```bash
192.168.3.10 harbor.litingli.com
```

##### 生成自签证书
Docker默认通过HTTPS与Harbor通信的，虽然可以改为HTTP方式，但需要修改的配置项会很多，而且也不安全;  
配套的CA证书自然是少不了的;
```bash
创建证书存放目录
mkdir -p /usr/local/harbor/cert && cd /usr/local/harbor/cert

生成根证书私钥（无加密）
openssl genrsa -out ca.key 4096

生成自签名证书（使用已有私钥ca.key自行签发根证书）
openssl req -x509 -new -nodes -sha512 -days 3650 -subj "/C=CN/ST=Beijing/L=Beijing/O=ccx/OU=plat/CN=192.168.3.10" -key ca.key -out ca.crt

生成服务器端自己域名的key
openssl genrsa -out harbor.litingli.com.key 4096

生成服务器端自己域名的CSR签名请求
openssl req -sha512 -new  -subj "/C=CN/ST=Beijing/L=Beijing/O=ccx/OU=plat/CN=192.168.3.10" -key harbor.litingli.com.key -out harbor.litingli.com.csr
```
##### 生成一个 openssl 命令需要的外部配置文件 externalfile.ext  
这个文件可以随意命名，但是要记住，后面对的命令还要用到。
文件内容中主要是subjectAltName这一项
如果配IP就写IP.1=192.168.xxx.xxx
如果配域名就写 DNS.1=xxx.xxx.com
```bash
vim externalfile.ext

authorityKeyIdentifier=keyid,issuer
basicConstraints=CA:FALSE
keyUsage = digitalSignature, nonRepudiation, keyEncipherment, dataEncipherment
extendedKeyUsage = serverAuth 
subjectAltName = @alt_names
[alt_names]
IP.1=192.168.3.10
DNS.1=harbor.litingli.com
```

##### 通过外部配置文件 externalfile.ext和 csr 生成 crt
```bash
openssl x509 -req -sha512 -days 3650 -extfile externalfile.ext -CA ca.crt -CAkey ca.key -CAcreateserial -in harbor.litingli.com.csr -out harbor.litingli.com.crt
```

##### 将服务端的 crt 转换成客户端用的 cert
```bash
openssl x509 -inform PEM -in harbor.litingli.com.crt -out harbor.litingli.com.cert
```
至此，所有的文件都已全部生成
```
[root@dev cert_harbor]# ll
total 32
-rw-r--r-- 1 root root 2017 Feb 23 13:44 ca.crt
-rw-r--r-- 1 root root 3243 Feb 23 13:42 ca.key
-rw-r--r-- 1 root root   17 Feb 23 13:53 ca.srl
-rw-r--r-- 1 root root  232 Feb 23 13:52 externalfile.ext
-rw-r--r-- 1 root root 2049 Feb 23 13:54 harbor.litingli.com.cert
-rw-r--r-- 1 root root 2049 Feb 23 13:53 harbor.litingli.com.crt
-rw-r--r-- 1 root root 1700 Feb 23 13:49 harbor.litingli.com.csr
-rw-r--r-- 1 root root 3247 Feb 23 13:47 harbor.litingli.com.key
```

##### 为各个docker客户端分发证书
将Harbor主机上带域名的.cert和.key证书文件拷贝到docker客户端所在主机的/etc/docker/certs.d/xxx.xxx.com/目录下。
下面以192.168.3.10这台docker客户端主机上的操作为例进行介绍  
###### 在Docker主机上执行：
```bash
 mkdir -p /etc/docker/certs.d/harbor.litingli.com/
```
###### 在Harbor主机，执行：
```bash
scp /usr/local/harbor/cert/harbor.litingli.com.cert /usr/local/harbor/cert/harbor.litingli.com.key root@192.168.3.10:/etc/docker/certs.d/harbor.litingli.com/
```
###### 在Docker主机修改 /etc/docker/daemon.json，主要是增加"insecure-registries":["http://harbor.litingli.com"]
```bash
[root@dev111 ~]# vim /etc/docker/daemon.json
{
  ...
  "insecure-registries":["http://harbor.cn"],
  ...
}
```
###### 重启Docker
```bash
systemctl daemon-reload && systemctl restart docker
```

##### 修改配置文件harbor.yml
```bash
[root@dev110 ~] vim /usr/local/harbor/harbor.yml
hostname: #IP地址或域名
http:
	port: 80
https:
	port: 443
	certificate: /usr/local/harbor/cert/harbor.litingli.com.crt # 这里是证书信息
	private_key: /usr/local/harbor/cert/harbor.litingli.com.key # 这里是证书信息
harbor_admin_password: ChinaNet910111 #  根据需要修改Web端admin用户的密码，默认为Harbor12345
database:
	password: ChinaNet910111  #  为harbor内置数据库root用户的密码，默认为root123
data_volumn: /data
log:
    level: info
    location: /usr/local/harbor/logs # harbor日志存放路径
```

##### 先更新参数
```bash
[root@dev110 ~]# cd /usr/local/harbor
[root@dev110 harbor]# ./prepare
```

##### 再进行安装
当你看到Harbor has been installed and started successfully时，我要恭喜你安装成功了。
```bash
[root@dev110 harbor]# ./install.sh
[Step 0]: checking if docker is installed ...
Note: docker version: 19.03.6
[Step 1]: checking docker-compose is installed ...
Note: docker-compose version: 1.25.4
[Step 2]: loading Harbor images ...
...
这里会很慢，因为要拉取很多镜像
...
[Step 3]: preparing environment ...
[Step 4]: preparing harbor configs ...
prepare base dir is set to /home/k8s/harbor
Generated configuration file: /config/log/logrotate.conf
...
Generated configuration file: /compose_location/docker-compose.yml
Clean up the input dir
[Step 5]: starting Harbor ...
WARNING: The Docker Engine you're using is running in swarm mode.
Compose does not use swarm mode to deploy services to multiple nodes in a swarm. All containers will be scheduled on the current node.
To deploy your application across the swarm, use `docker stack deploy`.
Creating network "harbor_harbor" with the default driver
Creating harbor-log ... done
Creating registry      ... done
Creating harbor-portal ... done
Creating harbor-db     ... done
Creating redis         ... done
Creating registryctl   ... done
Creating harbor-core   ... done
Creating nginx             ... done
Creating harbor-jobservice ... done
✔ ----Harbor has been installed and started successfully.----
```
