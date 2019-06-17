package com.zan.hu.auth.async;

import com.zan.hu.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.oauth2.common.ExpiringOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-04-11 20:53
 * @Description 异步处理生成的token 入库存Redis
 **/
@Component
@Slf4j
public class RedisAsyncTask {
    private static final String ACCESS_TO_REFRESH = "access_to_refresh:";

    private static final String REFRESH_TO_ACCESS = "refresh_to_access:";

    @Autowired
    private RedisService redisService;

    @Autowired
    ValueOperations<String, Object> valueOperations;

    @Async
    public void storeToken(OAuth2AccessToken oAuth2AccessToken) {
        log.info("异步任务正在处理中..........");
        Long accessTokenSeconds = Long.valueOf(oAuth2AccessToken.getExpiresIn());
        OAuth2RefreshToken refreshToken = oAuth2AccessToken.getRefreshToken();
        Map<String, Object> additionalInformation = oAuth2AccessToken.getAdditionalInformation();
        String guid = additionalInformation.get("guid").toString();
        if (refreshToken instanceof ExpiringOAuth2RefreshToken) {
            ExpiringOAuth2RefreshToken expiringRefreshToken = (ExpiringOAuth2RefreshToken) refreshToken;
            Date expiration = expiringRefreshToken.getExpiration();
            log.info("refresh token time:" + expiration);
            if (expiration != null) {
                Long seconds = (expiration.getTime() - System.currentTimeMillis()) / 1000L;
                redisService.set(REFRESH_TO_ACCESS + guid, seconds, oAuth2AccessToken.getRefreshToken().getValue());
            }
        }
        redisService.set(ACCESS_TO_REFRESH + guid, accessTokenSeconds, oAuth2AccessToken.getValue());
    }
}
