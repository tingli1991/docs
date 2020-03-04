#### Harbor使用教程
##### 参考资料
https://blog.csdn.net/jason9211/article/details/104464342/

##### push镜像：docker ==> harbor
要想将镜像push到Harbor仓库中，必须先要在Harbor中创建自己的项目，即project，当然也可以使用Harbor自带的项目：library
下面看看如何做才能吧nginx镜像推送到Harbor镜像中去。
* 第一步：docker拉取一个镜像并修改tag
```bash
docker pull nginx
docker tag nginx:latest harbor.cn/library/nginx:latest
```
* 第二步：docker login 登录Harbor：
```bash
# harbor_user_name - Harbor用户名
# harbor_password - 该Harbor用户的密码
# harbor_domain - Harbor的域名
docker login -u<harbor_user_name> -p<harbor_password> <harbor_domain>
```
* 执行实例以及输出
```bash
[root@MiWiFi-R2100-srv ~]# docker login https://harbor.litingli.com -u admin -p ChinaNet910111
WARNING! Using --password via the CLI is insecure. Use --password-stdin.
WARNING! Your password will be stored unencrypted in /root/.docker/config.json.
Configure a credential helper to remove this warning. See
https://docs.docker.com/engine/reference/commandline/login/#credentials-store

Login Succeeded
```

* 当看到Login Succeeded时，就说明登陆成功了。
* 下面可以查看docker中保存的登录信息：
```bash
[root@dev ~]# cat ~/.docker/config.json 
{
        "auths": {
                "harbor.cn": {
                        "auth": "Y2N4LWRldjpDY3hkZXYxMjM="
                }
        },
        ...
}
```

* 第三步：docker推送镜像到Harbor
```bash
# harbor_domain - Harbor的域名
# project_name - Harbor中的项目名称
# image_name - 镜像名称
# image_tag - 镜像tag
docker push <harbor_domain>/<project_name>/<image_name>:<image_tag>
```
* 执行命令，及输出：
```bash
[root@dev ~]# docker push harbor.cn/library/nginx:latest
The push refers to repository [harbor.cn/library/nginx]
22439467ad99: Pushed 
b4a29beac87c: Pushed 
488dfecc21b1: Pushed 
latest: digest: sha256:62f787b94e5faddb79f96c84ac0877aaf28fb325bfc3601b9c0934d4c107ba94 size: 948
```
