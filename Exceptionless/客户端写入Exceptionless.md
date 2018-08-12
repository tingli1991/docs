# 客户端写入Exceptionless
关测试代码见src目录的解决方案  
本文是建立在已经安装好Exceptionless的基础上，如果想了解如何安装相关教程请参考  
[Exceptionless简介]https://github.com/tingli1991/technology-stack/blob/master/Exceptionless/README.md  


### 1. .Net Core WebAPI项目测试
1.1 登录Exceptionless后台创建一个测试项目，具体流程如下：  
![新建项目流程1](https://github-1251498502.cos.ap-chongqing.myqcloud.com/Exceptionless/20180812141436_9.png)  
![新建项目流程2](https://github-1251498502.cos.ap-chongqing.myqcloud.com/Exceptionless/20180812141436_10.png)  
![新建项目流程3](https://github-1251498502.cos.ap-chongqing.myqcloud.com/Exceptionless/20180812141436_11.png)  
![新建项目流程4](https://github-1251498502.cos.ap-chongqing.myqcloud.com/Exceptionless/20180812141436_12.png)  
![新建项目流程5](https://github-1251498502.cos.ap-chongqing.myqcloud.com/Exceptionless/20180812141436_13.png)  

1.2 打开visual studio 2017并新建一个AspNetCore的WebAPI项目(项目名称和解决方案名称可以自己随便取)  
![新建的AspNet WebAPI项目](https://github-1251498502.cos.ap-chongqing.myqcloud.com/Exceptionless/20180812141436_14.png?radnm=1) 
