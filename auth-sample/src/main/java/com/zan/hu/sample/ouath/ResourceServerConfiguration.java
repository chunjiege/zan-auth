package com.zan.hu.sample.ouath;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-04-01 19:18
 * @Description 资源服务器配置
 **/
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    private static final String DEMO_RESOURCE_ID = "auth";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(DEMO_RESOURCE_ID).stateless(true);
    }
}
