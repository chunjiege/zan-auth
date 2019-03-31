package com.zan.hu.auth.controller;

import com.zan.hu.auth.ClientService;
import com.zan.hu.auth.GlobalUserService;
import com.zan.hu.auth.SysClient;
import com.zan.hu.auth.SysGlobalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-03-31 14:05
 * @Description todo
 **/
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private GlobalUserService globalUserService;

    @PostMapping("/create/client")
    public void createClient(@RequestBody SysClient sysClient) {
        clientService.create(sysClient);
    }

    @PostMapping("/register/global")
    public void register(@RequestBody SysGlobalUser sysGlobalUser) {
        globalUserService.register(sysGlobalUser);
    }
}
