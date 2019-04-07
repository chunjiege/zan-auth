package com.zan.hu.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-04-01 19:06
 * @Description todo
 **/
@SpringCloudApplication
@MapperScan("com.zan.hu.auth.mapper")
@EnableAspectJAutoProxy
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}