package com.zan.hu.auth.endpoint.handle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-06-10 11:04
 * @Description todo
 **/
@Slf4j
@Component
public class AuthenticationFailHandle extends AbstractAuthenticationFailEvenHandle {

    /**
     * 处理登录失败方法
     * <p>
     *
     * @param authenticationException 登录的authentication 对象
     * @param authentication          登录的authenticationException 对象
     */
    @Override
    public void handle(AuthenticationException authenticationException, Authentication authentication) {
        log.info("用户：{} 登录失败，异常：{}", authentication.getPrincipal(), authenticationException.getLocalizedMessage());
    }
}
