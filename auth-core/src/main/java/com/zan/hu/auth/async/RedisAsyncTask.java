package com.zan.hu.auth.async;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zan.hu.common.entity.AccessTokenDto;
import com.zan.hu.common.service.RedisService;
import com.zan.hu.common.utils.ObjectMapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-04-11 20:53
 * @Description 异步处理生成的token 入库存Redis
 **/
@Component
@Slf4j
public class RedisAsyncTask {

    private static Set<String> VALID_TOKEN = new CopyOnWriteArraySet();

    @Autowired
    private RedisService redisService;

    @Async
    public void setToken(OAuth2AccessToken oAuth2AccessToken) throws JsonProcessingException {
        log.info("异步任务正在进行中..........");
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        Map<String, Object> additionalInformation = oAuth2AccessToken.getAdditionalInformation();
        String guid = (additionalInformation.get("guid").toString());//guid id
        String tokenValue = oAuth2AccessToken.getValue();//token
        Integer expiresIn = oAuth2AccessToken.getExpiresIn();//秒..过期时间
        accessTokenDto.setAccessToken(tokenValue);
        accessTokenDto.setExpiresIn(new Date().getTime() + (expiresIn.longValue() * 1000));
        accessTokenDto.setGuid(guid);
        ObjectMapper objectMapper = ObjectMapperUtils.newInstance();
        String accessToken = objectMapper.writeValueAsString(accessTokenDto);
        log.info("accessToken:" + accessToken);
        VALID_TOKEN.add(accessToken);
        redisService.add(tokenValue, VALID_TOKEN);
    }
}
