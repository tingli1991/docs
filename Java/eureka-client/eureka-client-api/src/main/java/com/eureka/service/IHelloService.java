package com.eureka.service;

import com.eureka.model.ResponseData;
import com.eureka.param.HelloParam;
import com.eureka.result.HelloResult;

/**
 * @author Litingli
 * @createTime 26 17:22
 * @description
 */

public interface IHelloService {
    /**
     * @param:
     * @description: 获取hello信息
     * @author: litingli
     * @date: 2019/1/26
     * @return:
     */
    ResponseData<HelloResult> get(int id);

    /**
     * @param:
     * @description: 获取hello信息
     * @author: litingli
     * @date: 2019/1/26
     * @return:
     */
    ResponseData<HelloResult> post(HelloParam param);
}