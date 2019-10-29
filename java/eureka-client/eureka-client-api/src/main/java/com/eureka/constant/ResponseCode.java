package com.eureka.constant;

/**
 * @author Litingli
 * @createTime 26 16:47
 * @description 错误码信息枚举
 */
public enum ResponseCode {
    SUCCESS(0, "OK"),
    UNKNOWNFAILURE(-1, "未知处理结果"),
    UNKNOWNERROR(9999, "系统未知异常");

    private int code;
    private String message;

    private ResponseCode(int code, String message) {
        this.setCode(code);
        this.setMsg(message);
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.message;
    }

    public void setMsg(String message) {
        this.message = message;
    }
}