package com.zan.hu.auth.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-03-31 15:40
 * @Description todo
 **/
//@Configuration
//@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private static final String DEMO_RESOURCE_ID = "auth";

//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) {
//        resources.resourceId(DEMO_RESOURCE_ID).stateless(true);
//    }
}
