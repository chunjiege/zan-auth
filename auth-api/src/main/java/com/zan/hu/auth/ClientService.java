package com.zan.hu.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @version 1.0
 * @Author admin
 * @Date 2019-04-04 13:20
 * @Description todo
 **/
@RequestMapping("/api/client")
public interface ClientService {

    @PostMapping
    void createClient(@RequestBody SysClient sysClient);
}
