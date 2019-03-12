### SOPA协议  
#### 什么是SOAP协议？  
  SOAP(SimpleObjectaccessPRotocal，简单对象访问协议)是基于XML的简易对象访问协议，可以使应用程序在HTTP之上进行数据交换（或者更简单的说SOAP是用于访问网络服务的协议）。

#### SOAP协议都有哪些特点？  
- SOAP是**简易对象访问协议** 
- SOAP是一种**通信协议**  
- SOAP应用于**应用程序之间**的通信  
- SOAP是一种用于**发送信息**的消息格式  
- SOAP被设计用来通过**英特网进行通讯**  
- **独立于平台**  
- **独立于语言**  
- **基于XML**  
- **允许你绕过防火墙**  

#### 基于SOAP协议的  
- WebService  

#### Web Service和WebAPI的区别  
##### Web Service：  
- Web Service是基于SOAP协议，数据格式是XML；  
- 数据传输只支持HTTP协议；  
- 它的部署仅限于IIS；  
- 非开源的产品，但是可以被任意一个了解XML的人使用；
##### WebAPI：
- 是一个简单的用于构建HTTP协议的新框架；
- 数据的传输支持HTTP、HTTPS协议；
- 数据的格式比较自由 可以是json、xml以及用户自定义；
- 是.NET产品开源的、理想的、构建REST-Ful服务的技术；
- 它可以使用http协议的全部特点（例如：request/response以及缓存和版本控制等）；
- 可以部署在IIS 等其他的Host/Server服务器上；
- Response可以被Web API的MediaTypeFormatter转换成Json、XML 或者任何你想转换的格式；
- 轻量级的框架，并且对限制带宽的设备，比如智能手机等支持的很好；
