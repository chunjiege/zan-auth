package com.zan.hu.auth.config;

import com.zan.hu.jwt.ResourceServerConf;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-09-26 13:50
 * @Description todo
 **/
@EnableResourceServer
@Configuration
public class ResourceServer extends ResourceServerConf {

    private static final String RESOURCE_ID = "auth-server";

    public String resourceId() {
        return RESOURCE_ID;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated();
    }
}
