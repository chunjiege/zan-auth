package com.zan.hu.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-03-30 16:36
 * @Description todo
 **/
@ConfigurationProperties("security")
@Data
public class SecurityProperties {

    private JwtProperties jwt;

    @Data
    public static class JwtProperties {
        private String keyStorePassword;
        private String keyPairAlias;
        private String keyPairPassword;
        private Resource keyStore;
    }
}
