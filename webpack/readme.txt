//1.初始环境基础命令
// npm init 初始环境（生成 package.json 环境配置文件）
// npm install --production 安装依赖（生产环境）
// npm install 安装依赖(开发环境依赖)

//2.打包写好的js
// webpack src/js/index.js build/js/index.js

//3.webpack环境配置文件（webpack.config.js）

//4.webpack-dev-server搭建服务器
//  cnpm install webpack-dev-server -g 安装全局测试环境服务器
//  cnpm install webpack-dev-server -save-dev 将测试环境服务器安装到本地
//  webpack-dev-server  正常运行站点
//  webpack-dev-server --iFrame 在框架网页上运行站点（右键单击可以查看网页框架功能）
//  webpack-dev-server --inline 没有框架的方式运行

//5.全局化引入jquery实例（如果是本地化则以入口文件按照相对路径进行引入）
//var $ = require('jquery');
//$('#welcome').html('hello world');

//6.使用webpack打包css(如下两个包是必须的)
//cnpm install css-loader --save-dev
//cnpm install style-loader --save-dev

//7.css文件引入另外一个css文件使用import关键词（如下）
//@import url('./font.css');

//8.webpack 打包编译less文件
//cnpm install less-loader less -g
//cnpm install less-loader less --save-dev
//require('index.less');

//9.webpack 安装json-loader
//cnpm install json-loader less -g
//cnpm install json-loader less --save-dev

//10.webpack 安装babel相关包
//cnpm install babel-core babel-loader babel-preset-es2015 babel-preset-react -g
//cnpm install babel-core babel-loader babel-preset-es2015 babel-preset-react --save-dev

//11.抽离css样式,防止将样式打包在js中引起页面样式加载错乱的现象
//extract-text-webpack-plugin
//cnpm install extract-text-webpack-plugin -g
//cnpm install extract-text-webpack-plugin --save-dev

//12.生成html文件
//html-webpack-plugin 
//cnpm install html-webpack-plugin --save-dev

//13.webpack 压缩js插件
//cnpm install uglify-js -g
//cnpm install uglify-js --save-dev
//uglifyjs [编译入口文件]  -m -o [输出文件]  //-m 表示混淆代码 -o 表示输出路径

//webpack 压缩插件
//cnpm install uglifyjs-webpack-plugin -g
//cnpm install uglifyjs-webpack-plugin --save-dev

//图片的打包和压缩(两个插件)
//cnpm install file-loader url-loader -g
//cnpm install file-loader url-loader --save-dev

//14.html图片处理
//html-loader 插件

//15.环境变量
//set NODE_ENV=pro      //windows 设置环境变量
//export NODE_ENV=pro   //linux 设置环境变量

//16.总结
//webpack 只适合用来做单个页面的打包