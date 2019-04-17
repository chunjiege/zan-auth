package com.zan.hu.auth.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.util.StringUtils;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-04-11 13:15
 * @Description todo
 **/
@Configuration
public class FeignConfiguration implements RequestInterceptor {

    String BEARER_TYPE = "Bearer";

    String BLANK_SPACE = " ";

    String token = null;

    public FeignConfiguration() {
    }

    @Autowired
    private FakeToken fakeToken;


    @Override
    public void apply(RequestTemplate template) {
        if (StringUtils.isEmpty(token)) {
            getFakeToken();
        }
        template.header("Authorization", BEARER_TYPE + BLANK_SPACE + token);
    }

    public String getFakeToken() {
        OAuth2AccessToken oAuth2AccessToken = fakeToken.getOAuth2AccessToken();
        return token = oAuth2AccessToken.getValue();
    }
}
