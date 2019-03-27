package com.zan.hu.test;

import feign.codec.Encoder;
import org.springframework.beans.factory.ObjectFactory;
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

//    @Autowired
//    private ObjectFactory<HttpMessageConverters> messageConverters;

    @Bean
    @Primary
    @Scope("prototype")
    public Encoder feignFormEncoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        return new SpringMultipartEncoder(new SpringEncoder(messageConverters));
    }

}
