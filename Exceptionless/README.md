# Exceptionless 简介
&nbsp;&nbsp;&nbsp;&nbsp;Exceptionless 是一个开源的实时的日志收集框架，它可以应用在基于 ASP.NET，ASP.NET Core，Web Api，Web Forms，WPF，Console，MVC 等技术栈的应用程序中，并且提供了Rest接口可以应用在 Javascript，Node.js 中。它将日志收集变得简单易用并且不需要了解太多的相关技术细节及配置。
在以前，我们做日志收集大多使用 Log4net，Nlog 等框架，在应用程序变得复杂并且集群的时候，可能传统的方式已经不是很好的适用了，因为收集各个日志并且分析他们将变得麻烦而且浪费时间。

**官&nbsp;网：**  http://exceptionless.com  
**ElasticSearch地址：** https://www.elastic.co/downloads/elasticsearch  
**Exceptionless的GitHub地址：**  https://github.com/exceptionless/Exceptionless/releases  

**好文推荐：**
1. https://www.cnblogs.com/yilezhu/p/9339017.html  
2. https://www.cnblogs.com/uptothesky/p/5864863.html  
3. https://www.cnblogs.com/savorboard/p/exceptionless.html

**Exceptionless部署，需要服务器环境为：**  
**1. 项目依赖**  
**ElasticSearch**  
&nbsp;&nbsp;&nbsp;&nbsp;ElasticSearch是一个基于Lucene的搜索服务器。它提供了一个分布式多用户能力的全文搜索引擎，基于RESTful web接口。Elasticsearch是用Java开发的，并作为Apache许可条款下的开放源码发布，是当前流行的企业级搜索引擎。设计用于云计算中，能够达到实时搜索，稳定，可靠，快速，安装使用方便。  
**2. 环境依赖**  
&nbsp;&nbsp;&nbsp;&nbsp;2.1 NET 4.6.1  
&nbsp;&nbsp;&nbsp;&nbsp;2.3 IIS7.5+  
&nbsp;&nbsp;&nbsp;&nbsp;2.2 Java JDK 1.8+  
&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:red">**注意：**</span>环境依赖于Java JDK 1.8+ 是因为ElasticSearch依赖所导致，ElasticSearch项目是可以独立部署的；  

**步骤1. 安装ElasticSearch**  
&nbsp;&nbsp;&nbsp;&nbsp;关于ElasticSearch的安装步骤建议参考博客园的文章，里面讲得很详细，而且也讲到了安装过程中的一些常见问题；  
[***参考地址***] https://www.cnblogs.com/lizichao1991/p/7809156.html  
[**注意：**] 推荐安装ElasticSearch的版本小于6.0,否则会导致如下的错误：
![Exceptionless启动时的异常](https://github-1251498502.cos.ap-chongqing.myqcloud.com/Exceptionless/20180812141436_1.png)  

**步骤2. Exceptionless到IIS**  
**2.1 下载Exceptionless的发布包**  
下载地址(目前的版本是4.1，建议下载最新的zip包即可)：https://github.com/exceptionless/Exceptionless/releases  
下载后的解压效果图如下：  
![Exceptionless解压后的效果图](https://github-1251498502.cos.ap-chongqing.myqcloud.com/Exceptionless/20180812141436_2.png)  
**2.1 部署IIS**  
在IIS中添加一个web站点，站点指向的物理路径为Exceptionless目录下的wwwroot目录，应用程序池设置为net4.0 集成模式  
![Exceptionless的IIS部署效果图](https://github-1251498502.cos.ap-chongqing.myqcloud.com/Exceptionless/20180812141436_3.png)   

**步骤3. 修改IIS站点的配置文件**  
下图就是此次需要修改的两个配置文件  
**注意：** 文件app.config.77fc9ddd679d37dc.js可能每个版本都不一样，所以只要看到 **app.config.*.js**即可  
![要修改的配置文件](https://github-1251498502.cos.ap-chongqing.myqcloud.com/Exceptionless/20180812141436_5.png)  
**3.1 修改web.config配置**
![需要调整的配置](https://github-1251498502.cos.ap-chongqing.myqcloud.com/Exceptionless/20180812141436_4.png)  

**3.2 修改app.config.*.js**  
![需要调整的配置](https://github-1251498502.cos.ap-chongqing.myqcloud.com/Exceptionless/20180812141436_6.png)

完成以上的步骤之后，我们就可以正常使用Exceptionless啦，效果图如下：  
![安装完成后的效果图1](https://github-1251498502.cos.ap-chongqing.myqcloud.com/Exceptionless/20180812141436_7.png)  
![安装完成后的效果图1](https://github-1251498502.cos.ap-chongqing.myqcloud.com/Exceptionless/20180812141436_8.png)  
