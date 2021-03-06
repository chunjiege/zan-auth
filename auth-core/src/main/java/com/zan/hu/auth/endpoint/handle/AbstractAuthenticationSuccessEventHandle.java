package com.zan.hu.auth.endpoint.handle;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.util.CollectionUtils;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-06-10 10:39
 * @Description 认证成功事件处理器
 **/
public abstract class AbstractAuthenticationSuccessEventHandle implements ApplicationListener<AuthenticationSuccessEvent> {

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        Authentication authentication = (Authentication) event.getSource();
        if (!CollectionUtils.isEmpty(authentication.getAuthorities())) {
            handle(authentication);
        }
    }

    /**
     * 处理登录成功方法
     * <p>
     * 获取到登录的authentication 对象
     *
     * @param authentication 登录对象
     */
    public abstract void handle(Authentication authentication);
}
