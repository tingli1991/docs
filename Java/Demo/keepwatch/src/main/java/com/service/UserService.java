package com.service;

import javax.servlet.http.HttpServletRequest;
import com.service.param.LoginParam;
import com.service.result.ResponseModel;

/** 用户服务 */
public interface UserService {

    public ResponseModel getUserList(String userName, String realName);

    public ResponseModel getUserByAccessToken(String accessToken);

    public ResponseModel login(LoginParam param, HttpServletRequest request);

    public ResponseModel loginOut(String accessToken);
}