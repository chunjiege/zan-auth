package com.zan.hu.auth.constant;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-04-17 09:05
 * @Description todo
 **/
public interface OauthConstant {

    String CLIENT_ID = "admin";

    String CLIENT_SECRET = "admin";

    String SCOPE = "read,write";

    String AUTO_APPROVE_SCOPES = ".*";

    String RESOURCE_IDS = "account-server";

    String AUTHORIZED_GRANT_TYPES = "authorization_code, password, client_credentials, implicit, refresh_token,sms_code";

    Integer ACCESS_TOKEN_VALIDITY_SECONDS = 2 * 60;

    Integer REFRESH_TOKEN_VALIDITY_SECONDS = 2 * 60;

    String AUTHORITIES = "ROLE_USER";

    String USERNAME = "hupeng";

    String PASSWORD = "hupeng";

    String GRANT_TYPE = "password";
}
