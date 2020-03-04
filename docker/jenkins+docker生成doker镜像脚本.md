#### 参考资料
 https://blog.csdn.net/u013711462/article/details/80219693
 
#### Dockerfile文件
注意：由于框架结构的原因 该文件需要放在.sln所在的文件目录
```C#
#指定父镜像为netcore的sdk
FROM microsoft/aspnetcore/sdk:3.0 AS build

#工作目录
WORKDIR /app/HY.Home.UC/src

#将当前目录下的文件，复制到WORKDIR目录
COPY . .

#查看WORKDIR目录下面的文件
RUN ls -l

#工作目录调整到 /app/HY.Home.UC/src/HY.Home.UC 目录
WORKDIR /app/HY.Home.UC/src/HY.Home.UC

#更新WORKDIR目录对应项目的nuget
RUN dotnet restore "HY.Home.UC.csproj" -s https://api.nuget.org/v3/index.json -s http://nuget.litingli.com/nuget

#查看/app/HY.Home.UC/src/HY.Home.UC目录下面的文件
RUN ls -l

#执行编译命令
RUN dotnet build "HY.Home.UC.csproj" -c Release -o /app/HY.Home.UC/build



#指定父镜像为.net core的sdk进行发布操作
FROM build AS publish

#发布dotnet项目到/app/HY.Home.UC/publish目录
RUN dotnet publish "HY.Home.UC.csproj" -c Release -o /app/HY.Home.UC/publish



#指定父镜像为netcore的运行时
FROM microsoft/aspnetcore:3.0 AS base

#指定工作目录为 /app/HY.Home.UC/publish
WORKDIR /app/HY.Home.UC/publish

#指定外部访问的端口
EXPOSE 8301

#从编译阶段的中拷贝编译结果到当前镜像中
COPY --from=publish /app/HY.Home.UC/publish .

#执行命令
ENTRYPOINT ["dotnet", "HY.Home.UC.dll"]
```

#Jenkins 需要执行的shell命令
``` shell
#!/bin/bash
# 切换到源码目录,对应Dockerfile所在目录
cd HY.Home.UC

#赋予该文件夹下面所有文件执行权限
chmod u+x -R *

#查看文件夹下面的内容
ls -l

#声明容器名称
image_name=hy/home/uc;
echo $image_name;

#声明镜像版本号&输出版本号
#image_version=`date +%Y%m%d%H%M`; #获取动态的版本号
image_version=1.0.0;
echo $image_version;

#判断容器是否存在
docker ps -a | grep $image_name &> /dev/null
if [ $? -ne 1 ]
then
   # 停止之前的docker container
   docker stop $image_name;
   echo '容器停止成功！！！';

   # 删除这个container
   docker rm $image_name;
   echo '容器删除成功！！！';images
fi

#判断容器是否存在
docker images | grep $image_name &> /dev/null
if [ $? -ne 1 ]
then
   # 删除这个镜像
   docker rmi $image_name:$image_version;
   echo '镜像删除成功！！！';
fi

#登陆docker hub
#docker login -u tingli1991 -p ChinaNet910111

# build镜像并且打上tag
docker build -t $image_name:$image_version .;

#查看镜像
docker images | grep $image_name;
```
