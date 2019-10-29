package com.eureka.controller;

import com.eureka.model.ResponseData;
import com.eureka.param.HelloParam;
import com.eureka.result.HelloResult;
import com.eureka.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Litingli
 * @createTime 26 17:38
 * @description
 */
@RestController
public class HelloController {

    @Autowired
    public HelloService helloService;

    @RequestMapping(value = "/hello/get", method = RequestMethod.GET)
    public ResponseData<HelloResult> get(int id) {
        return helloService.get(id);
    }

    @RequestMapping(value = "/hello/post", method = RequestMethod.POST)
    public ResponseData<HelloResult> post(@RequestBody HelloParam param) {
        return helloService.post(param);
    }
}