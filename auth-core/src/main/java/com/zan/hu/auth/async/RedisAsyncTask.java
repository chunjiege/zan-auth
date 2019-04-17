package com.zan.hu.auth.async;

import com.zan.hu.auth.service.impl.RedisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-04-11 20:53
 * @Description 异步处理生成的token 入库存Redis
 **/
@Component
public class RedisAsyncTask {

    private static Set<String> VALID_TOKEN = new CopyOnWriteArraySet();

    @Autowired
    private RedisServiceImpl redisService;

    @Async
    public void setToken(OAuth2AccessToken oAuth2AccessToken) {
//        Map<String, Object> additionalInformation = oAuth2AccessToken.getAdditionalInformation();
//        String guid = (additionalInformation.get("guid").toString());//guid id
        String tokenValue = oAuth2AccessToken.getValue();//token
//        int expiresIn = oAuth2AccessToken.getExpiresIn();//秒..过期时间
        VALID_TOKEN.add(tokenValue);
        redisService.add("VALID_TOKEN", VALID_TOKEN);
    }
}
