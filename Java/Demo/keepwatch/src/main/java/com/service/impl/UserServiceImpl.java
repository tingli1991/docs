package com.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.mapper.UserMapper;
import com.service.result.ResponseModel;
import com.model.User;
import com.mysql.cj.util.StringUtils;
import com.service.UserService;
import com.service.param.LoginParam;
import com.utils.MD5Util;
import com.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMap;

    public ResponseModel getUserList(String userName, String realName) {
        ResponseModel result = new ResponseModel();
        List<User> userList = userMap.getUserList(userName, realName);
        if (userList != null && !userList.isEmpty()) {
            // 返回数据报文
            result.setData(userList);
            result.setSuccess(true);
        }
        return result;
    }

    public ResponseModel getUserByAccessToken(String accessToken) {
        ResponseModel result = new ResponseModel();
        User user = userMap.getUserByAccessToken(accessToken);
        if (user != null) {
            result.setSuccess(true);
            result.setData(user);
        }
        return result;
    }

    public ResponseModel loginOut(String accessToken) {
        ResponseModel result = new ResponseModel();
        if (accessToken == null || accessToken.isEmpty()) {
            result.setErrcode("100000");
            result.setErrmsg("参数异常");
            return result;
        }
        User user = userMap.getUserByAccessToken(accessToken);
        if (user == null) {
            result.setErrcode("100004");
            result.setErrmsg("用户信息不存在");
            return result;
        }
        Date date = new Date();
        user.setUpdatetime(date);
        user.setAccesstoken("");
        int subNumber = userMap.updateByKey(user);
        result.setSuccess(subNumber > 0);
        return result;
    }

    public ResponseModel login(LoginParam param, HttpServletRequest request) {
        ResponseModel response = new ResponseModel();
        if (param == null || StringUtils.isNullOrEmpty(param.getUsername())
                || StringUtils.isNullOrEmpty(param.getPassword())) {
            response.setErrcode("100000");
            response.setErrmsg("参数异常");
            return response;
        }

        String userName = param.getUsername();
        User user = userMap.getUserByUserName(userName);
        if (user == null) {
            response.setErrcode("100101");
            response.setErrmsg("用户名或密码错误！");
            return response;
        }

        String password = param.getPassword();
        if (!user.getPassword().toUpperCase().equals(password.toUpperCase())) {
            response.setErrcode("100102");
            response.setErrmsg("用户名或密码错误！");
            return response;
        }

        // 用户名&密码输入正确生成AccessToken并返回给前端
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMdd");
        String dataStr = dataFormat.format(new Date()) + RandomUtil.getNewRandomCode(6);
        String accessToken = MD5Util.encrypt(dataStr);

        // 更新用户token
        User entity = new User();
        entity.setId(user.getId());
        entity.setAccesstoken(accessToken);
        entity.setUpdatetime(new Date());
        int handlerNumber = userMap.updateByKey(entity);
        if (handlerNumber > 0) {
            // 返回数据报文
            response.setData(user);
            response.setSuccess(true);
        }
        return response;
    }
}