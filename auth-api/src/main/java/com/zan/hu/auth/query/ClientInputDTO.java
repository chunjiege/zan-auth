package com.zan.hu.auth.query;

import lombok.Data;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-05-22 19:51
 * @Description todo
 **/
@Data
public class ClientInputDTO {

    private String clientId;

    private String clientSecret;

    private String resourceIds;

    private String scope;

    private String authorities;
}
