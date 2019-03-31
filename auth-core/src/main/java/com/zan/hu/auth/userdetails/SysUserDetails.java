package com.zan.hu.auth.userdetails;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-03-30 23:42
 * @Description todo
 **/
public interface SysUserDetails extends UserDetails {

    /**
     * get sys GUID
     *
     * @return
     */
    String getUserGuid();
}
