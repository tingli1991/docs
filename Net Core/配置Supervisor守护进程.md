## 配置Supervisor守护进程  
#### 1.安装 supervisor  
```BASH
yum install python-setuptools
easy_install supervisor
```
#### 2.创建配置目录并生成配置文件  
```bash
mkdir /etc/supervisor
echo_supervisord_conf > /etc/supervisor/supervisord.conf
```
#### 3.修改supervisord.conf文件,将文件尾部的配置,修改为如下内容:
```bash
[include]
files = conf.d/*.conf
```
#### 4.启动supervisord
```bash
supervisord -c /etc/supervisor/supervisord.conf
```
#### 5.创建一个AnuoApc.conf 文件，内容如下：
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
#### 6.将文件拷贝至：“/etc/supervisor/conf.d/”下，并输入如下命令即可完成整个流程：
```bash
supervisorctl reload              使其生效.
supervisorctl start acore.dll     启动指定的项目
ps -ef | grep AnuoApc             查看指定的项目AutoApc是否生效
```
## 常用命令集  
|    命令                         |      说明                                                                                                                                             |
|---------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------|
| supervisord                     | 初始启动Supervisord，启动、管理配置中设置的进程                                                                                                       |
| supervisorctl stop myproject    | 停止某一个进程(programxxx)，programxxx为[program:chatdemon]里配置的值，这个示例就是chatdemon                                                          |
| supervisorctl start myproject   | 启动某个进程                                                                                                                                          |
| supervisorctl restart myproject | 重启某一个进程                                                                                                                                        |
| supervisorctl stop groupworker  | 重启所有属于名为groupworker这个分组的进程(start,restart同理)                                                                                          |
| supervisorctl stop all          | 停止全部进程，注：start、restart、stop都不会载入最新的配置文件                                                                                        |
| supervisorctl reload            | 载入最新的配置文件，停止原有进程并按新的配置启动、管理所有进程                                                                                        |
| supervisorctl update            | 根据最新的配置文件，启动新配置或有改动的进程，配置没有改动的进程不会受影响而重启。<br/>注意：显示用stop停止掉的进程，用reload或者update都不会自动重启 |
| ps -ef \| grep supervisord      | 查看supervisord进程                                                                                                                                   |

## 制作Supervisor开机启动(附加内容)  
#### 1.新建一个“supervisord.service”文件,内容如下：
```bash
# dservice for systemd (CentOS 7.0+)
# by ET-CS (https://github.com/ET-CS)
[Unit]
Description=Supervisor daemon

[Service]
Type=forking
ExecStart=/usr/bin/supervisord -c /etc/supervisor/supervisord.conf
ExecStop=/usr/bin/supervisorctl shutdown
ExecReload=/usr/bin/supervisorctl reload
KillMode=process
Restart=on-failure
RestartSec=42s

[Install]
WantedBy=multi-user.target
```
#### 2.将supervisord文件拷贝（或者移动）到“/usr/lib/systemd/system/supervisord.service”：
```bash
mv supervisord.service /usr/lib/systemd/system/supervisord.service
``` 
#### 3.启动
```bash
systemctl enable supervisord       启动supervisord
systemctl is-enabled supervisord   验证一下是否为开机启动
```
