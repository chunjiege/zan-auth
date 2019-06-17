package com.zan.hu.auth.config;

import com.zan.hu.auth.constant.OauthConstant;
import com.zan.hu.auth.oauth.AuthorizationServerConfiguration;
import com.zan.hu.auth.userdetails.CurrentAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.util.StringUtils;

import java.security.KeyPair;
import java.util.Arrays;
import java.util.Collection;
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

    @Autowired
    private SecurityProperties securityProperties;

    private DefaultTokenServices defaultTokenServices = new DefaultTokenServices();

    private DefaultOAuth2RequestFactory defaultOAuth2RequestFactory = new DefaultOAuth2RequestFactory(new fakeClientDetailsService());

    public FakeToken() {
    }

    public OAuth2AccessToken getOAuth2AccessToken() {
        defaultTokenServices.setTokenStore(new JwtTokenStore(update()));
        defaultTokenServices.setTokenEnhancer(update());
        defaultTokenServices.setClientDetailsService(new fakeClientDetailsService());
        TokenRequest tokenRequest = defaultOAuth2RequestFactory.createTokenRequest(buildLinkedHashMap(), buildClientDetails());
        Authentication userAuth = buildAuthentication(tokenRequest);
        OAuth2Request storedOAuth2Request = defaultOAuth2RequestFactory.createOAuth2Request(buildClientDetails(), tokenRequest);
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(storedOAuth2Request, userAuth);
        OAuth2AccessToken accessToken = defaultTokenServices.createAccessToken(oAuth2Authentication);
        return accessToken;
    }

    public ClientDetails buildClientDetails() {
        BaseClientDetails clientDetails = new BaseClientDetails();
        clientDetails.setClientId(OauthConstant.CLIENT_ID);
        clientDetails.setClientSecret(passwordEncoder.encode(OauthConstant.CLIENT_SECRET));
        clientDetails.setScope(Arrays.asList(StringUtils.tokenizeToStringArray(OauthConstant.SCOPE, ",")));
        clientDetails.setAutoApproveScopes(Arrays.asList(StringUtils.tokenizeToStringArray(OauthConstant.AUTO_APPROVE_SCOPES, ",")));
        clientDetails.setResourceIds(Arrays.asList(StringUtils.tokenizeToStringArray(OauthConstant.RESOURCE_IDS, ",")));
        clientDetails.setAuthorizedGrantTypes(Arrays.asList(StringUtils.tokenizeToStringArray(OauthConstant.AUTHORIZED_GRANT_TYPES, ", ")));
        clientDetails.setAccessTokenValiditySeconds(OauthConstant.ACCESS_TOKEN_VALIDITY_SECONDS);
        clientDetails.setRefreshTokenValiditySeconds(OauthConstant.REFRESH_TOKEN_VALIDITY_SECONDS);
        clientDetails.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(OauthConstant.AUTHORITIES));
        return clientDetails;
    }

    public Map<String, String> buildLinkedHashMap() {
        Map<String, String> parameters = new LinkedHashMap<>();
        parameters.put("grant_type", OauthConstant.GRANT_TYPE);
        parameters.put("username", OauthConstant.USERNAME);
        parameters.put("password", OauthConstant.PASSWORD);
        parameters.put("client_id", OauthConstant.CLIENT_ID);
        parameters.put("client_secret", OauthConstant.CLIENT_SECRET);
        return parameters;
    }

    class fakeClientDetailsService implements ClientDetailsService {

        @Override
        public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
            return buildClientDetails();
        }
    }

    private Authentication buildAuthentication(TokenRequest tokenRequest) {
        Map<String, String> parameters = new LinkedHashMap<String, String>(tokenRequest.getRequestParameters());
        String username = parameters.get("username");
        String password = parameters.get("password");
        // Protect from downstream leaks of password
        parameters.remove("password");
        Authentication userAuth = new UsernamePasswordAuthenticationToken(username, password);
        ((AbstractAuthenticationToken) userAuth).setDetails(parameters);
        CurrentAccount globalUser = fakeSysAccount();
        Object principal = globalUser;
        return createSuccessAuthentication(principal, userAuth, globalUser);
    }

    private CurrentAccount fakeSysAccount() {
        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setGuid(UUID.randomUUID().toString().replace("-", ""));
        currentAccount.setUsername(OauthConstant.USERNAME);
        currentAccount.setPassword(passwordEncoder.encode(OauthConstant.PASSWORD));
        currentAccount.setLocked(Boolean.TRUE);
        currentAccount.setEnabled(Boolean.TRUE);
        currentAccount.setExpired(Boolean.TRUE);
        currentAccount.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(OauthConstant.AUTHORITIES)));
        return currentAccount;
    }

    private Authentication createSuccessAuthentication(Object principal, Authentication authentication, UserDetails user) {
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(OauthConstant.AUTHORITIES);
        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(
                principal, authentication.getCredentials(),
                authorities);
        result.setDetails(authentication.getDetails());

        return result;
    }

    private JwtAccessTokenConverter update() {
        KeyStoreKeyFactory keyStoreKeyFactory = AuthorizationServerConfiguration.keyStoreKeyFactory(securityProperties.getJwt());
        KeyPair keyPair = AuthorizationServerConfiguration.keyPair(securityProperties.getJwt(), keyStoreKeyFactory);
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setKeyPair(keyPair);
        return jwtAccessTokenConverter;
    }

}
