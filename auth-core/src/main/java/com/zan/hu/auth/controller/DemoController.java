package com.zan.hu.auth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-09-23 11:12
 * @Description todo
 **/
@RestController
public class DemoController {

    @GetMapping("/user")
    public Authentication getUser(Authentication authentication) {
        System.out.println("resource: user " + authentication);
        return authentication;
    }
}
