
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

### 引入jQuery实例
``` javascript
var $ = require('jquery');
$('#welcome').html('hello world');
```
### webpack.config.js 介绍
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
