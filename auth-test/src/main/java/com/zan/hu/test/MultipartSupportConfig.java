package com.zan.hu.test;

import feign.Logger;
import feign.codec.Encoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

/**
 * @version 1.0
 * @Author admin
 * @Date 2019-03-27 10:58
 * @Description todo
 **/
@Configuration
public class MultipartSupportConfig {

    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;//支持form表单登录

    @Bean
    @Primary
    @Scope("prototype")
    public Encoder feignFormEncoder() {
        return new SpringMultipartEncoder(new SpringEncoder(messageConverters));
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}
