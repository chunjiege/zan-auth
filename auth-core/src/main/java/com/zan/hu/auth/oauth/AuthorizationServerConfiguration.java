package com.zan.hu.auth.oauth;

import com.zan.hu.auth.config.CustomerEnhancer;
import com.zan.hu.auth.config.SecurityProperties;
import com.zan.hu.auth.oauth.client.ClientDetailsServiceImpl;
import com.zan.hu.auth.userdetails.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;

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

//    @Autowired
//    private TokenStore tokenStore;

    @Autowired
    private ClientDetailsServiceImpl clientDetailsService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private DefaultTokenServices defaultTokenServices;

    @Autowired
    private RedefineTokenGranter redefineTokenGranter;

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
                .tokenStore(tokenStore())//.tokenEnhancer(jwtAccessTokenConverter())
                .tokenGranter(redefineTokenGranter)
                .authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .userDetailsService(userDetailsService);
        endpoints.tokenServices(defaultTokenServices);
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
        JwtAccessTokenConverter converter = new CustomerEnhancer();
        converter.setKeyPair(keyPair);
        return converter;
    }

    public static KeyPair keyPair(SecurityProperties.JwtProperties jwtProperties, KeyStoreKeyFactory keyStoreKeyFactory) {
        return keyStoreKeyFactory.getKeyPair(jwtProperties.getKeyPairAlias());
    }

    public static KeyStoreKeyFactory keyStoreKeyFactory(SecurityProperties.JwtProperties jwtProperties) {
        return new KeyStoreKeyFactory(jwtProperties.getKeyStore(), jwtProperties.getKeyStorePassword().toCharArray());
    }
}
