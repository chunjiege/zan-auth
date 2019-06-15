package com.zan.hu.auth.endpoint.handle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-06-10 10:47
 * @Description todo
 **/
@Slf4j
@Component
public class AuthenticationSuccessHandleHandle extends AbstractAuthenticationSuccessEventHandle {
    @Override
    public void handle(Authentication authentication) { 
        log.info("用户：{} 登录成功",authentication.getPrincipal());
    }
}
