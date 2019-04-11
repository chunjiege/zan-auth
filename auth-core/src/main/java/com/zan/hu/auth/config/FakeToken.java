package com.zan.hu.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken;
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-04-11 12:54
 * @Description todo
 **/
public class FakeToken {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();

    private DefaultTokenServices defaultTokenServices = new DefaultTokenServices();

    private DefaultOAuth2RequestFactory defaultOAuth2RequestFactory = new DefaultOAuth2RequestFactory(new fakeClientDetailsService());

    public FakeToken() {
    }

    public OAuth2AccessToken getOAuth2AccessToken() {
        defaultTokenServices.setTokenStore(new JwtTokenStore(jwtAccessTokenConverter));
        defaultTokenServices.setTokenEnhancer(jwtAccessTokenConverter);
        defaultTokenServices.setClientDetailsService(new fakeClientDetailsService());
        TokenRequest tokenRequest = defaultOAuth2RequestFactory.createTokenRequest(buildLinkedHashMap(), buildClientDetails());
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("USER");
        Authentication userAuth = new UsernamePasswordAuthenticationToken("hupeng", "hupeng", authorities);
        OAuth2Request oAuth2Request = defaultOAuth2RequestFactory.createOAuth2Request(buildClientDetails(), tokenRequest);
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, userAuth);
        OAuth2AccessToken accessToken = defaultTokenServices.createAccessToken(oAuth2Authentication);
        return accessToken;
    }

    public ClientDetails buildClientDetails() {
        BaseClientDetails clientDetails = new BaseClientDetails();
        clientDetails.setClientId("admin");
        clientDetails.setClientSecret(passwordEncoder.encode("admin"));
        clientDetails.setScope(Arrays.asList(StringUtils.tokenizeToStringArray("read,write", ",")));
        clientDetails.setAutoApproveScopes(Arrays.asList(StringUtils.tokenizeToStringArray(".*", ",")));
        clientDetails.setResourceIds(Arrays.asList(StringUtils.tokenizeToStringArray("sys-server", ",")));
        clientDetails.setAuthorizedGrantTypes(Arrays.asList(StringUtils.tokenizeToStringArray("authorization_code, password, client_credentials, implicit, refresh_token,sms_code", ", ")));
        clientDetails.setAccessTokenValiditySeconds(86400);
        clientDetails.setRefreshTokenValiditySeconds(86400);
        clientDetails.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList("USER"));
        return clientDetails;
    }

    public Map<String, String> buildLinkedHashMap() {
        Map<String, String> parameters = new LinkedHashMap<>();
        parameters.put("grant_type", "password");
        parameters.put("username", "hupeng");
        parameters.put("password", "hupeng");
        parameters.put("client_id", "admin");
        parameters.put("client_secret", "admin");
        return parameters;
    }

    class fakeClientDetailsService implements ClientDetailsService {

        @Override
        public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
            return buildClientDetails();
        }
    }
}
