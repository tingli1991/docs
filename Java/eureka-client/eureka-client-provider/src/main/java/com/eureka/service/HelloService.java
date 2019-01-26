package com.eureka.service;

import com.eureka.model.ResponseData;
import com.eureka.param.HelloParam;
import com.eureka.result.HelloResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author Litingli
 * @createTime 26 17:42
 * @description
 */
@Service("helloService")
public class HelloService implements IHelloService {

    @Autowired
    public DiscoveryClient _client;

    private final Logger logger = Logger.getLogger("");


    @Override
    public ResponseData<HelloResult> get(int id) {
        ResponseData<HelloResult> response = new ResponseData<HelloResult>();
        try {
            List<String> serviceList = _client.getServices();
            ServiceInstance instance = _client.getLocalServiceInstance();
            String host = instance.getHost();
            int port = instance.getPort();
            String serviceId = instance.getServiceId();
            Map<String, String> metadata = instance.getMetadata();
            URI uri = instance.getUri();
            logger.info("/hello, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
        } catch (Exception ex) {

        }
        return response;
    }

    /**
     * @param:
     * @description:
     * @author: litingli
     * @date: 2019/1/26
     * @return:
     */
    @Override
    public ResponseData<HelloResult> post(HelloParam param) {
        ResponseData<HelloResult> response = new ResponseData<HelloResult>();
        try {

        } catch (Exception ex) {

        }
        return response;
    }
}
