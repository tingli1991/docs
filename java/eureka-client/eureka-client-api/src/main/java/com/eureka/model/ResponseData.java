package com.eureka.model;

import com.eureka.constant.ResponseCode;

public class ResponseData<T> {
    private T data;

    private int code;

    private String msg;

    private Boolean success;

    public ResponseData() {
        //设置默认的错误信息
        responseCode(ResponseCode.UNKNOWNFAILURE);
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * @param:
     * @description: 设置成功返回结果
     * @author: litingli
     * @date: 2019/1/26
     * @return:
     */
    public void ok() {
        this.success = true;
        this.code = ResponseCode.SUCCESS.getCode();
        this.msg = ResponseCode.SUCCESS.getMsg();
    }

    /**
     * @param:
     * @description: 判断是否成功
     * @author: litingli
     * @date: 2019/1/26
     * @return:
     */
    public Boolean isSuccess() {
        int code = ResponseCode.SUCCESS.getCode();
        String msg = ResponseCode.SUCCESS.getMsg();
        return this.success == true && this.code == code && this.msg == msg;
    }

    /**
     * @param:
     * @description:
     * @author: litingli
     * @date: 2019/1/26
     * @return:
     */
    public void responseCode(ResponseCode responseCode) {
        this.success = false;
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
    }
}