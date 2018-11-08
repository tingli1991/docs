package com.service.result;

import java.util.Date;

public class ResponseModel {
    /** 构造函数 */
    public ResponseModel() {
        success = false;
        serviceTime = new Date();
    }

    // 服务器时间
    private Date serviceTime;
    // 业务是否处理成功（true：表示业务成功；false：表示业务异常需要弹错误信息）
    private Boolean success;
    // 错误代码
    private String errcode;
    // 错误消息
    private String errmsg;
    // 业务正常返回的数据
    private Object data;

    // 获取服务器时间
    public Date getServiceTime() {
        return serviceTime;
    }

    // 设置服务器时间
    public void setId(Date serviceTime) {
        this.serviceTime = serviceTime;
    }

    // 获取业务处理结果
    public Boolean getSuccess() {
        return success;
    }

    // 设置业务处理状态
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    // 获取错误码
    public String getErrcode() {
        return errcode;
    }

    // 设置错误码
    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    // 获取错误消息
    public String getErrmsg() {
        return errmsg;
    }

    // 设置错误消息
    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    // 获取业务数据
    public Object getData() {
        return data;
    }

    // 设置业务数据
    public void setData(Object data) {
        this.data = data;
    }
}