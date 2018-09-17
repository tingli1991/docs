# 配置Supervisor守护进程  
### 1.安装 supervisor  
```BASH
yum install python-setuptools
easy_install supervisor
```

### 2.创建配置目录并生成配置文件  
```bash
mkdir /etc/supervisor
echo_supervisord_conf > /etc/supervisor/supervisord.conf
```

### 3.修改supervisord.conf文件,将文件尾部的配置,修改为如下内容:
```bash
[include]
files = conf.d/*.conf
```

### 4.启动supervisord
```bash
supervisord -c /etc/supervisor/supervisord.conf
```

### 5.创建一个AnuoApc.conf 文件，内容如下：
```bash
[program:AnuoApc]
command=dotnet AnuoApc.Web.dll ;                运行程序的命令
directory=/root/aspnetcore/anuoapc/ ;           命令执行的目录
autorestart=true ;                              程序意外退出是否自动重启
stderr_logfile=/var/log/AnuoApc.err.log ;       错误日志文件
stdout_logfile=/var/log/AnuoApc.out.log ;       输出日志文件
environment=ASPNETCORE_ENVIRONMENT=Production ; 进程环境变量
user=root ;                                     进程执行的用户身份
stopsignal=INT
```

### 6.将文件拷贝至：“/etc/supervisor/conf.d/”下，并输入如下命令即可完成整个流程：
```bash
supervisorctl reload              使其生效.
supervisorctl start acore.dll     启动指定的项目
ps -ef | grep AnuoApc             查看指定的项目AutoApc是否生效
```
