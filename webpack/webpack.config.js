<<<<<<< HEAD
﻿var webpack = require('webpack');

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
=======
﻿var webpack = require('webpack');

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
>>>>>>> ab7c07b5e4f7d2a7ba5381bea4904c54049efb2b
}