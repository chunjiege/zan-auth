package com.zan.hu.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-04-11 14:33
 * @Description todo
 **/
@Configuration
public class BeanConfig {

    @Bean
    public FakeToken fakeToken() {
        return new FakeToken();
    }

}
