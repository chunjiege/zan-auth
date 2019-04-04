package com.zan.hu.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @version 1.0
 * @Author admin
 * @Date 2019-04-04 13:21
 * @Description todo
 **/
@RequestMapping("/api/global")
public interface GlobalUserService {

    @PostMapping
    void register(@RequestBody SysGlobalUser sysGlobalUser);
}
