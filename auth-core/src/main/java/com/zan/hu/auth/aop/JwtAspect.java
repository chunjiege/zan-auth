package com.zan.hu.auth.aop;

import com.zan.hu.auth.domain.GlobalUser;
import com.zan.hu.auth.dao.mapper.GlobalUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.Map;

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
    private GlobalUserMapper globalUserMapper;

    @Pointcut("execution(* org.springframework.security.oauth2.provider.endpoint.TokenEndpoint.postAccessToken(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
    }

    @AfterReturning(returning = "ret", pointcut = "pointcut()")
    public void doAfterReturning(Object ret) throws Throwable {
        ResponseEntity<OAuth2AccessToken> responseEntity = (ResponseEntity<OAuth2AccessToken>) ret;
        OAuth2AccessToken oAuth2AccessToken = responseEntity.getBody();
        Map<String, Object> additionalInformation = oAuth2AccessToken.getAdditionalInformation();
        Long id = Long.valueOf(additionalInformation.get("id").toString());
        Example example = getExample(id);
        GlobalUser globalUser = globalUserMapper.selectOneByExample(example);
        globalUser.setAccessToken(oAuth2AccessToken.getValue());
        DefaultExpiringOAuth2RefreshToken refreshToken = (DefaultExpiringOAuth2RefreshToken) oAuth2AccessToken.getRefreshToken();
        globalUser.setRefreshToken(refreshToken.getValue());
        globalUser.setExpiresIn(refreshToken.getExpiration());
        int result = globalUserMapper.updateByPrimaryKeySelective(globalUser);
        // 处理完请求，返回内容
        log.info("RESPONSE : " + ret);
    }

    private Example getExample(Long id) {
        Example example = new Example(GlobalUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        return example;
    }
}
