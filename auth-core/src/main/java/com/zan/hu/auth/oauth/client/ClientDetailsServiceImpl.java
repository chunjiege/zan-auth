package com.zan.hu.auth.oauth.client;

import com.zan.hu.auth.Client;
import com.zan.hu.auth.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-03-30 21:48
 * @Description todo
 **/
@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        Client client = clientMapper.selectByClientId(clientId);
        BaseClientDetails clientDetails = new BaseClientDetails();
        clientDetails.setClientId(client.getClientId());
        clientDetails.setClientSecret(client.getClientSecret());
        clientDetails.setScope(Arrays.asList(StringUtils.tokenizeToStringArray(client.getScope(), ",")));
        clientDetails.setAutoApproveScopes(Arrays.asList(StringUtils.tokenizeToStringArray(client.getAutoApprove(), ",")));
        clientDetails.setResourceIds(Arrays.asList(StringUtils.tokenizeToStringArray(client.getResourceIds(), ",")));
        clientDetails.setAuthorizedGrantTypes(Arrays.asList(StringUtils.tokenizeToStringArray(client.getAuthorizedGrantTypes(), ", ")));
        clientDetails.setAccessTokenValiditySeconds(client.getAccessTokenValidity());
        clientDetails.setRefreshTokenValiditySeconds(client.getRefreshTokenValidity());
        if (StringUtils.hasText(client.getAuthorities())) {
            clientDetails.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(client.getAuthorities()));
        }
        return clientDetails;
    }
}
