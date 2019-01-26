package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {
    /**
     * @param:
     * @description:
     * @author: litingli
     * @date: 2019/1/26
     * @return:
     */
    public EurekaApplication() {
    }

    /**
     * @param:
     * @description: Eureka服务端主函数
     * @author: litingli
     * @date: 2019/1/26
     * @return:
     */
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }
}