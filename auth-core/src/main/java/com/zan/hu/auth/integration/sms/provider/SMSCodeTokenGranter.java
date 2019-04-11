package com.zan.hu.auth.integration.sms.provider;

import com.zan.hu.auth.config.ApplicationContextUtil;
import com.zan.hu.auth.service.RedisService;
import com.zan.hu.auth.service.impl.RedisServiceImpl;
import com.zan.hu.auth.userdetails.UserDetailsServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-04-08 20:56
 * @Description todo
 **/
public class SMSCodeTokenGranter extends AbstractTokenGranter {

    private UserDetailsServiceImpl userDetailsService;

    private RedisService redisService;

    private static final String GRANT_TYPE = "sms_code";


    public SMSCodeTokenGranter(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        super(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        userDetailsService = (UserDetailsServiceImpl) ApplicationContextUtil.getBean("userDetailsServiceImpl");
        redisService = (RedisServiceImpl) ApplicationContextUtil.getBean("redisServiceImpl");
    }


    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {

        Map<String, String> parameters = new LinkedHashMap<>(tokenRequest.getRequestParameters());
        String username = parameters.get("username");  //客户端提交的用户名
        String code = parameters.get("code");  //客户端提交的验证码

        // 从库里查用户
        UserDetails user = userDetailsService.loadUserByUsername(username);//从库里查找用户的代码;
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        //从缓存中查询code
        String cacheCode = redisService.get(username).toString();
        if (StringUtils.isEmpty(cacheCode)) {
            throw new InvalidGrantException("验证码已经过期！");
        }
        // 验证验证码
        if (StringUtils.isBlank(code)) {
            throw new InvalidGrantException("验证码为空！");
        }
        boolean validate = Objects.equals(cacheCode, code);
        if (validate) {
            throw new InvalidGrantException("验证码不正确");
        } else {
            redisService.remove(code);
        }
        Authentication userAuth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        ((AbstractAuthenticationToken) userAuth).setDetails(parameters);
        OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
        return new OAuth2Authentication(storedOAuth2Request, userAuth);
    }

}
