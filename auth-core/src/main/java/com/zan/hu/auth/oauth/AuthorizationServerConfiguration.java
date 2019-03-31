package com.zan.hu.auth.oauth;

import com.zan.hu.auth.clientdetails.ClientDetailsServiceImpl;
import com.zan.hu.auth.security.SecurityProperties;
import com.zan.hu.auth.userdetails.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.security.KeyPair;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-03-30 16:38
 * @Description todo
 **/
@Configuration
@EnableAuthorizationServer
@EnableConfigurationProperties(SecurityProperties.class)
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ClientDetailsServiceImpl clientDetailsService;

//    @Autowired
//    private SecurityProperties securityProperties;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;




    @Autowired
    private TokenStore tokenStore;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients(); // allow check token;
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
        endpoints.userDetailsService(userDetailsService);

        endpoints.approvalStoreDisabled();
//        ProviderManager providerManager = (ProviderManager) authenticationManager;
//        providerManager.getProviders().clear();
//        providerManager.getProviders().add(multiAuthenticationProvider);
        //.authenticationManager(providerManager)
        endpoints
                .tokenStore(tokenStore);//存储token到Redis

    }
    @Configuration
    protected static class RelatedConfiguration {
        @Bean
        public TokenStore tokenStore(RedisConnectionFactory connectionFactory) {
            RedisTokenStore redisTokenStore = new RedisTokenStore(connectionFactory);
            return redisTokenStore;
        }
    }







//------------------------------------
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {


//        endpoints.authenticationManager(authenticationManager)
//                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
//                .tokenStore(jwtTokenStore())
//                .tokenEnhancer(jwtTokenConverter());
//        endpoints.tokenStore(jwtTokenStore())
//                .authenticationManager(authenticationManager).accessTokenConverter(jwtTokenConverter())
//                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)  //支持GET  POST  请求获取token
//                .userDetailsService(userDetailsService); //必须注入userDetailsService否则根据refresh_token无法加载用户信息
   // }








//    @Bean
//    public TokenStore jwtTokenStore() {
//        return new JwtTokenStore(jwtTokenConverter());
//    }

//    @Bean
//    protected JwtAccessTokenConverter jwtTokenConverter() {
//        SecurityProperties.JwtProperties jwtProperties = securityProperties.getJwt();
//        //KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("auth-jwt.jks"), "followme".toCharArray());
//        KeyPair keyPair = keyPair(jwtProperties, keyStoreKeyFactory(jwtProperties));
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        //converter.setKeyPair(keyStoreKeyFactory.getKeyPair("auth-jwt"));
//        converter.setKeyPair(keyPair);
//        return converter;
//    }
//
//
//    private KeyPair keyPair(SecurityProperties.JwtProperties jwtProperties, KeyStoreKeyFactory keyStoreKeyFactory) {
//        return keyStoreKeyFactory.getKeyPair(jwtProperties.getKeyPairAlias(), jwtProperties.getKeyPairPassword().toCharArray());
//    }
//
//    private KeyStoreKeyFactory keyStoreKeyFactory(SecurityProperties.JwtProperties jwtProperties) {
//        return new KeyStoreKeyFactory(jwtProperties.getKeyStore(), jwtProperties.getKeyStorePassword().toCharArray());
//    }
}
