package com.zan.hu.auth.aop;

import com.zan.hu.auth.async.RedisAsyncTask;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-04-07 13:47
 * @Description todo
 **/
@Aspect
@Component
@Slf4j
public class JwtAspect {

    @Autowired
    private RedisAsyncTask redisAsyncTask;

    @Pointcut("execution(* org.springframework.security.oauth2.provider.endpoint.TokenEndpoint.postAccessToken(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        System.out.println(joinPoint);
    }

    @AfterReturning(returning = "ret", pointcut = "pointcut()")
    public void doAfterReturning(Object ret) throws Throwable {
        ResponseEntity<OAuth2AccessToken> responseEntity = (ResponseEntity<OAuth2AccessToken>) ret;
        OAuth2AccessToken oAuth2AccessToken = responseEntity.getBody();
        redisAsyncTask.setToken(oAuth2AccessToken);
        // 处理完请求，返回内容
        log.info("RESPONSE : " + ret);
    }
}
