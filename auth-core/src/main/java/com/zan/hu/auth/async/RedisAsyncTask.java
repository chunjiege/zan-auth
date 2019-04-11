package com.zan.hu.auth.async;

import com.zan.hu.auth.service.impl.RedisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-04-11 20:53
 * @Description 异步处理生成的token 入库存Redis
 **/
@Service
public class RedisAsyncTask {

    @Autowired
    private RedisServiceImpl redisService;

    public void setToken(OAuth2AccessToken oAuth2AccessToken) {
        Map<String, Object> additionalInformation = oAuth2AccessToken.getAdditionalInformation();
        String guid = (additionalInformation.get("guid").toString());//guid id
        String value = oAuth2AccessToken.getValue();//token
        int expiresIn = oAuth2AccessToken.getExpiresIn();//秒..过期时间
        redisService.add(guid, expiresIn, value);
    }
}
