package com.zan.hu.auth.endpoint;

import com.zan.hu.auth.config.ApplicationContextUtil;
import com.zan.hu.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-06-06 16:58
 * @Description 用户注销
 **/
@RestController
@Slf4j
public class RevokeTokenEndpoint {

    private static final String ACCESS_TO_REFRESH = "access_to_refresh:";

    private static final String REFRESH_TO_ACCESS = "refresh_to_access:";

    @Autowired
    private RedisService redisService;

    private TokenStore tokenStore;

    @DeleteMapping("/oauth/token/logout")
    public void exit(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authHeader) {
        if (StringUtils.isEmpty(authHeader)) {
            log.info("user logout fail，token is null");
            return;
        }
        String tokenValue = authHeader.replace(OAuth2AccessToken.BEARER_TYPE, "").trim();
        OAuth2AccessToken accessToken = getTokenStore().readAccessToken(tokenValue);
        if (accessToken == null || StringUtils.isEmpty(accessToken.getValue())) {
            log.info("user logout fail，token invalid");
            return;
        }
        Map<String, Object> additionalInformation = accessToken.getAdditionalInformation();
        String guid = additionalInformation.get("guid").toString();
        String accessToRefreshKey = ACCESS_TO_REFRESH + guid;
        String refreshToAccessKey = REFRESH_TO_ACCESS + guid;
        redisService.remove(accessToRefreshKey, refreshToAccessKey);
        log.info("user logout successfully");
    }

    private TokenStore getTokenStore() {
        if (tokenStore == null) {
            this.tokenStore = (TokenStore) ApplicationContextUtil.getBean("tokenStore");
        }
        return this.tokenStore;
    }
}
