package com.zan.hu.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-04-01 19:06
 * @Description 启动类
 **/
@SpringCloudApplication
@EnableAspectJAutoProxy
@EnableAsync
@ServletComponentScan
@ComponentScan("com.zan.hu")
@MapperScan("com.zan.hu.auth.mapper")
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
