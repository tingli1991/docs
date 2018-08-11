# Exceptionless 简介
&nbsp;&nbsp;&nbsp;&nbsp;Exceptionless 是一个开源的实时的日志收集框架，它可以应用在基于 ASP.NET，ASP.NET Core，Web Api，Web Forms，WPF，Console，MVC 等技术栈的应用程序中，并且提供了Rest接口可以应用在 Javascript，Node.js 中。它将日志收集变得简单易用并且不需要了解太多的相关技术细节及配置。
在以前，我们做日志收集大多使用 Log4net，Nlog 等框架，在应用程序变得复杂并且集群的时候，可能传统的方式已经不是很好的适用了，因为收集各个日志并且分析他们将变得麻烦而且浪费时间。

**官网：**  http://exceptionless.com  
**GitHub**  https://github.com/exceptionless/Exceptionless/releases

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
&nbsp;&nbsp;&nbsp;&nbsp;[***参考地址***] https://www.cnblogs.com/lizichao1991/p/7809156.html
