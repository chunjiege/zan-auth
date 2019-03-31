package com.zan.hu.auth;

import lombok.Data;

import java.util.UUID;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-03-31 00:18
 * @Description todo
 **/
@Data
public class SysGlobalUser {

    private String account;

    private String password;

    public String getGuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
