package com.zan.hu.sample.userdetails;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-03-30 23:42
 * @Description todo
 **/
public interface SysAccountDetails extends UserDetails {

    /**
     * get sys GUID
     *
     * @return
     */
    String getUserGuid();
}