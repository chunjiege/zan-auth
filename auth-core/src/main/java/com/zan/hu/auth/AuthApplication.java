package com.zan.hu.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-03-30 15:57
 * @Description todo
 **/
@SpringCloudApplication
@MapperScan("com.zan.hu.auth.mapper")
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
