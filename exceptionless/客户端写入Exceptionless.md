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
1.3 使用Nuget命令或者Nuget管理工具添加**Exceptionless.AspNetCore**包(我采用：Nuget程序包管理器来完成)
![添加NuGet包](https://github-1251498502.cos.ap-chongqing.myqcloud.com/Exceptionless/20180812141436_15.png?radnm=1)   

1.4 在Startup.cs文件中初始化相关配置
在Startup.cs文件中添加Exceptionless的引用  
``` C#
using Exceptionless;
```  
然后在Configure方法中添加Exceptionless管道信息  
``` C#
ExceptionlessClient.Default.Configuration.ApiKey = Configuration.GetSection("Exceptionless:ApiKey").Value;
ExceptionlessClient.Default.Configuration.ServerUrl = Configuration.GetSection("Exceptionless:ServerUrl").Value;
app.UseExceptionless();
```  
在appsettings.json中添加apikey以及serverurl的配置  
``` C#
"Exceptionless": {
    "ApiKey": "T1uijEtbtbNcPs0aI1izX6cyscuZbowQKYmEbyUH",
    "ServerUrl": "http://192.168.3.18:81"
  }
```  

1.5 代码中使用异常，具体测试代码如下代码段
``` C#
using Microsoft.AspNetCore.Mvc;
using System;

namespace Exceptionless.WebAPITest.Controllers
{
    /// <summary>
    /// 
    /// </summary>
    [Route("api/[controller]"), ApiController]
    public class ValuesController : ControllerBase
    {
        // GET api/values
        [HttpGet]
        public ActionResult<string> Get()
        {
            try
            {
                throw new Exception("ExceptionDemo 的异常");
            }
            catch (Exception ex)
            {
                //核心代码：向Exceptionless提交异常信息
                ex.ToExceptionless().Submit();
            }
            return "";
        }
    }
}
```  
**Exceptionless后台的日志效果**
![效果图1](https://github-1251498502.cos.ap-chongqing.myqcloud.com/Exceptionless/20180812141436_16.png)  
![效果图2](https://github-1251498502.cos.ap-chongqing.myqcloud.com/Exceptionless/20180812141436_17.png)
![效果图3](https://github-1251498502.cos.ap-chongqing.myqcloud.com/Exceptionless/20180812141436_18.png)  

### 2. 使用总结  
其实不管是AspNetCore WebAPI还是 Asp.Net Mvc 以及 Asp.Net WebAPI 他们唯一的区别就在于引入的报名以及配置文件的配置方式不同而已，以此类推，其他项目就不一一介绍了；
