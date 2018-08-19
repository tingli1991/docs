
### 初始化webpack环境
``` Bash
npm init                    初始化（生成 package.json环境配置文件）
npm install webpack -g      全局安装webpack（-g 替换成 --save-dev 表示本地开发环境）
npm install                 安装开发环境依赖包（后面新增参数 --production 则表示安装生产环境依赖包）
```

### 使用webpack命令打包js
``` Bash
webpack [源码文件路径] [打包文件路径]
```

### webpack-dev-server搭建服务器
``` Bash
npm install webpack-dev-server -g               全局安装开发环境服务器（-g 替换成 --save-dev 表示本地开发环境）
webpack-dev-server                              正常运行站点
webpack-dev-server --inline                     没有框架的方式运行
```
### 常用插件介绍
```
css-loader					css打包插件（配合style-loader一起使用）
style-loader					css打包插件（配合css-loader一起使用）
less-loader                                     less打包编译
json-loader                                     json解析器
html-loader                                     html里面的文件处理（例如：图片）
uglify-js                                       js压缩以及js代码混淆
extract-text-webpack-plugin                     分离css文件需要使用的插件（将js与css分开打包）
html-webpack-plugin                             生成html文件
uglifyjs-webpack-plugin				webpack的js压缩以及代码混淆
file-loader 和 url-loade                        图片、文件的打包和压缩
```

### 引入jQuery实例
``` javascript
var $ = require('jquery');
$('#welcome').html('hello world');
```
### package.json 常用配置
``` javascript
{
  "name": "www",
  "version": "1.0.0",
  "description": "",
  "main": "index.js",
  "author": "",
  "license": "ISC",
  "scripts": {
    "pro":"set NODE_ENV=pro&& webpack",
    "dev":"set NODE_ENV=dev&& webpack",
    "start": "webpack-dev-server --inline",
    "linux-pro":"export NODE_ENV=pro&& webpack"
  },
  "devDependencies": {
    "babel-core": "^6.26.0",
    "babel-loader": "^7.1.2",
    "babel-preset-es2015": "^6.24.1",
    "babel-preset-react": "^6.24.1",
    "css-loader": "^0.28.7",
    "extract-text-webpack-plugin": "^3.0.2",
    "file-loader": "^1.1.6",
    "html-loader": "^0.5.4",
    "html-webpack-plugin": "^2.30.1",
    "jquery": "^3.2.1",
    "less": "^2.7.3",
    "less-loader": "^4.0.5",
    "style-loader": "^0.19.1",
    "uglify-js": "^3.3.4",
    "uglifyjs-webpack-plugin": "^1.1.5",
    "url-loader": "^0.6.2"
  }
}

```

### webpack.config.js 常用配置
``` javascript
//引入webpack
var webpack = require('webpack');

//生成html文件插件
var HtmlWebpackPlugin = require('html-webpack-plugin');

//分离css插件
var ExtractTextPlugin = require("extract-text-webpack-plugin");
var ExtractCSS = new ExtractTextPlugin("css/index.css");

//webpack 推荐使用的 js压缩插件
var UglifyJsPlugin = require('uglifyjs-webpack-plugin');

module.exports = {
    entry: {
        vendor: ["jquery"],
        many: [
            __dirname + "/src/js/main.js",
            __dirname + "/src/js/home/index.js",
            __dirname + "/src/js/home/add.js"
        ]
    },
    output: {
        filename: "js/index.js",
        path: __dirname + "/build/",
	publicPath:'http://192.168.3.11:3000/'
    },
    devServer: {
        port: "3000",
	host: "192.168.3.11",
        contentBase: "./build/"
    },
    module: {
        loaders: [
		    {
                test: /\.css$/,
                loader: ExtractCSS.extract({
                    use: "css-loader",
                    fallback: "style-loader"
                })
            },
            {
                test: /\.jpg|.png|.jpeg$/,
                loader: "file-loader?limit=500&name=imgs/[hash:8].[name].[ext]"
            },
            {
                test: /\.html$/,
                loader: "html-loader"
            }
        ]
    },
    plugins: [
        ExtractCSS,
        new UglifyJsPlugin({
            test: /\.js/
        }),
        new HtmlWebpackPlugin({
            title: 'My App',
            inject: 'body',
            filename: 'index.html',
            template: 'src/template/index.html'
        }),
        new webpack.ProvidePlugin({
            $: 'jquery',
            jQuery: 'jquery'
        }),
        new webpack.HotModuleReplacementPlugin(),
        //共享插件模块
        new webpack.optimize.CommonsChunkPlugin({
            name: "vendor",
            filename: "lib/jquery.min.js",
        }),
        new webpack.optimize.CommonsChunkPlugin({
            filename: "js/[name].js",
            names: ["main", "index", "add"]
        })
    ]
}

if(process.env.NODE_ENV==='dev'){
	//这是开发环境
	console.log('开发环境');
	module.exports.watch=true;
}else{
	//这是生产环境
	console.log('生产环境');
	module.exports.watch=false;
}
```
