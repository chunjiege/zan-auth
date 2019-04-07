package com.zan.hu.auth.oauth;

import com.zan.hu.auth.config.SecurityProperties;
import com.zan.hu.auth.userdetails.SysAccount;
import com.zan.hu.auth.userdetails.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-04-01 19:15
 * @Description 认证服务器配置
 **/
@Configuration
@EnableAuthorizationServer
@EnableConfigurationProperties(SecurityProperties.class)
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    //注入权限验证控制器 来支持 password grant type
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private ClientDetailsServiceImpl clientDetailsService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private SecurityProperties securityProperties;

    public AuthorizationServerConfiguration(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore).tokenEnhancer(jwtAccessTokenConverter())
                .authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .userDetailsService(userDetailsService);
        // 配置tokenServices参数
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(endpoints.getTokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(clientDetailsService);
        //tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
        //tokenServices.setAccessTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30)); // 30天
        endpoints.tokenServices(tokenServices);
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        //允许表单认证
        oauthServer.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }


    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        KeyStoreKeyFactory keyStoreKeyFactory = keyStoreKeyFactory(securityProperties.getJwt());
        KeyPair keyPair = keyPair(securityProperties.getJwt(), keyStoreKeyFactory);
        JwtAccessTokenConverter converter = additionalInfo();
        converter.setKeyPair(keyPair);
        return converter;
    }

    private JwtAccessTokenConverter additionalInfo() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter() {
            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
                String account = authentication.getUserAuthentication().getName();
                SysAccount sysAccount = (SysAccount) userDetailsService.loadUserByUsername(account);
                // 得到用户名，去处理数据库可以拿到当前用户的信息和角色信息（需要传递到服务中用到的信息）
                final Map<String, Object> additionalAccountInfo = new HashMap<>();
                additionalAccountInfo.put("guid", sysAccount.getUserGuid());
                additionalAccountInfo.put("id", sysAccount.getId());
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalAccountInfo);
                OAuth2AccessToken enhancedToken = super.enhance(accessToken, authentication);
                return enhancedToken;
            }
        };
        return jwtAccessTokenConverter;
    }

    private KeyPair keyPair(SecurityProperties.JwtProperties jwtProperties, KeyStoreKeyFactory keyStoreKeyFactory) {
        return keyStoreKeyFactory.getKeyPair(jwtProperties.getKeyPairAlias());
    }

    private KeyStoreKeyFactory keyStoreKeyFactory(SecurityProperties.JwtProperties jwtProperties) {
        return new KeyStoreKeyFactory(jwtProperties.getKeyStore(), jwtProperties.getKeyStorePassword().toCharArray());
    }


    // @Autowired TokenStore，需要如下配置，否则包找不到TokenStore异常
//    @Configuration
//    protected static class RelatedConfiguration {
//        @Bean
//        public TokenStore tokenStore(RedisConnectionFactory connectionFactory) {
//            RedisTokenStore redisTokenStore = new RedisTokenStore(connectionFactory);
//            //EnhanceRedisTokenStore redisTokenStore = new EnhanceRedisTokenStore(connectionFactory);
//            return redisTokenStore;
//        }
//    }
}
