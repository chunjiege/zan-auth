package com.zan.hu.auth.config;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-04-06 23:42
 * @Description todo
 **/
public class ClientDetailsHolder {

    private static final String KEY = ClientDetails.class.getName();

    public static ClientDetails get() {
        return (ClientDetails) RequestContextHolder.getRequestAttributes().getAttribute(KEY, RequestAttributes.SCOPE_REQUEST);
    }

    public static void set(ClientDetails clientDetails) {
        RequestContextHolder.getRequestAttributes().setAttribute(KEY, clientDetails, RequestAttributes.SCOPE_REQUEST);
    }
}
