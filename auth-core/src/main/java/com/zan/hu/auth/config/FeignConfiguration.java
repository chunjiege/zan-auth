package com.zan.hu.auth.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-04-11 13:15
 * @Description todo
 **/
@Configuration
@Slf4j
public class FeignConfiguration implements RequestInterceptor {

    String BEARER_TYPE = "Bearer";

    String BLANK_SPACE = " ";

    OAuth2AccessToken oAuth2AccessToken = null;


    public FeignConfiguration() {
    }

    @Autowired
    private FakeToken fakeToken;


    @Override
    public void apply(RequestTemplate template) {
        if (oAuth2AccessToken == null || new Date(new Date().getTime() + (oAuth2AccessToken.getExpiresIn() * 1000)).before(new Date()) || StringUtils.isEmpty(oAuth2AccessToken.getValue())) {
            getFakeToken();
        }
        if (oAuth2AccessToken != null) {
            System.out.println(oAuth2AccessToken.getValue());
            template.header("Authorization", BEARER_TYPE + BLANK_SPACE + oAuth2AccessToken.getValue());
            log.info("请求uri:" + template.url() + ";请求方法:" + template.method());
        }
    }

    public void getFakeToken() {
        this.oAuth2AccessToken = fakeToken.getOAuth2AccessToken();
    }

}
