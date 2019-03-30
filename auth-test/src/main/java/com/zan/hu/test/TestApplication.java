package com.zan.hu.test;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @version 1.0
 * @Author admin
 * @Date 2019-03-27 09:31
 * @Description todo
 **/
@SpringCloudApplication
@EnableFeignClients
@EnableHystrix
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
