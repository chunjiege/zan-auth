package com.zan.hu.auth.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

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

    AtomicInteger atomicInteger = new AtomicInteger(1);

    public FeignConfiguration() {
    }

    @Autowired
    private FakeToken fakeToken;


    @Override
    public void apply(RequestTemplate template) {
        if (oAuth2AccessToken == null || new Date(new Date().getTime() + (oAuth2AccessToken.getExpiresIn() * 1000)).before(new Date()) || StringUtils.isEmpty(oAuth2AccessToken.getValue())) {
            log.info("第" + atomicInteger.get() + "次mock请求token");
            getFakeToken();
            atomicInteger.getAndIncrement();
        }
        if (oAuth2AccessToken != null) {
            template.header("Authorization", BEARER_TYPE + BLANK_SPACE + oAuth2AccessToken.getValue());
            log.info("请求uri:" + template.url() + ";请求方法:" + template.method() + "；请求token:" + oAuth2AccessToken.getValue());
        }
    }

    public OAuth2AccessToken getFakeToken() {
        return oAuth2AccessToken = fakeToken.getOAuth2AccessToken();
    }

}
