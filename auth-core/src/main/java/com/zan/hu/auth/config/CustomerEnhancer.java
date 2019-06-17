package com.zan.hu.auth.config;

import com.zan.hu.auth.feign.UserClient;
import com.zan.hu.auth.userdetails.CurrentAccount;
import com.zan.hu.auth.userdetails.UserDetailsServiceImpl;
import com.zan.hu.user.model.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-04-16 19:09
 * @Description todo
 **/
public class CustomerEnhancer extends JwtAccessTokenConverter {

    private UserDetailsServiceImpl userDetailsService;

    UserClient userClient = null;

    public CustomerEnhancer() {
        this.userDetailsService = (UserDetailsServiceImpl) ApplicationContextUtil.getBean("userDetailsServiceImpl");
    }

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        String account = authentication.getUserAuthentication().getName();
        CurrentAccount currentAccount = (CurrentAccount) userDetailsService.loadUserByUsername(account);
        // 得到用户名，去处理数据库可以拿到当前用户的信息和角色信息（需要传递到服务中用到的信息）
        User user = userClient().selectByGuid(currentAccount.getUserGuid());
        final Map<String, Object> additionalAccountInfo = new HashMap<>();
        additionalAccountInfo.put("guid", currentAccount.getUserGuid());
        additionalAccountInfo.put("id", currentAccount.getId());
        additionalAccountInfo.put("user", user);
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalAccountInfo);
        OAuth2AccessToken enhancedToken = super.enhance(accessToken, authentication);
        return enhancedToken;
    }

    private UserClient userClient() {
        if (this.userClient == null) {
            this.userClient = (UserClient) ApplicationContextUtil.getBean("user-server" + "FeignClient");
        }
        return this.userClient;
    }
}
