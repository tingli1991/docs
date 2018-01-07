
### 初始化webpack环境
    npm init                    初始化（生成 package.json环境配置文件）
    npm install webpack -g      全局安装webpack（-g 替换成 --save-dev 表示本地开发环境）
    npm install                 安装开发环境依赖包（后面新增参数 --production 则表示安装生产环境依赖包）
    
### 使用webpack命令打包js
    webpack [源码文件路径] [打包文件路径]

### webpack-dev-server搭建服务器
    npm install webpack-dev-server -g               全局安装开发环境服务器（-g 替换成 --save-dev 表示本地开发环境）
    webpack-dev-server                              正常运行站点
    webpack-dev-server --inline                     没有框架的方式运行

### 引入jquery
#### 在单个js里面引入jquery
``` javascript
var $ = require('jquery');
$('#welcome').html('hello world');
```
