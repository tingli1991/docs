//日期过滤器
Vue.filter('formatDate', function (time, fmt) {
    var date = new Date(time);
    if (!fmt) {
        fmt = 'yyyy-MM-dd hh:mm:ss';
    }

    if (!time || time == '') {
        return '';
    }
    return date.format(fmt);
})