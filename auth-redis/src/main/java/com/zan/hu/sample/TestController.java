package com.zan.hu.sample;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-04-01 19:20
 * @Description todo
 **/
@RestController
@RequestMapping
public class TestController {

    @RequestMapping(value = "order/demo")
    public Object getDemo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth);
        return auth.getPrincipal();
    }

    @GetMapping("/test")
    public String getTest() {
        return "hello word";
    }
}
