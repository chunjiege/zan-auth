package com.zan.hu.auth;

import lombok.Data;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-03-31 00:35
 * @Description todo
 **/
@Data
public class SysClient {

    private String clientId;

    private String clientSecret;

    private String resourceIds;

    private String scope;

    private String authorities;
}
