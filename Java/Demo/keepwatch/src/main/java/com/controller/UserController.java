package com.controller;

import com.service.result.ResponseModel;
import javax.servlet.http.HttpServletRequest;
import com.service.UserService;
import com.service.param.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping("/getUserByAccessToken")
    public ResponseModel getUserByAccessToken(String accessToken) {
        return userService.getUserByAccessToken(accessToken);
    }

    @ResponseBody
    @GetMapping("/getUserList")
    public ResponseModel getUserList(String userName, String realName) {
        return userService.getUserList(userName, realName);
    }

    @ResponseBody
    @PostMapping("/login")
    public ResponseModel login(@RequestBody LoginParam param, HttpServletRequest request) {
        return userService.login(param, request);
    }

    @ResponseBody
    @PostMapping("/loginOut")
    public ResponseModel loginOut(String accessToken) {
        return userService.loginOut(accessToken);
    }
}