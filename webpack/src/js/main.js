////引入jquery(单个页面引入)
//var $ = require('jquery');

//引入json
var stuJson = require('../json/stu.json');
txt = 'my name is ' + stuJson.name;

//jquery 输出hello world
$('#welcome').html('hello world ' + '<p>' + txt + '</p>');

//使用define加载json文件
define(["../json/stu.json"], function (stuJsonValue) {
    console.log(stuJsonValue);
    $('#welcome').append('<p>my name is' + stuJsonValue.name + '(define+append实现)</p>');
});

//css-loader
require("../css/index.css");
//document.write("<h1>Hello World</h1>");