package com.zan.hu.auth.feign;

import com.zan.hu.sys.domain.Account;
import com.zan.hu.sys.domain.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-04-10 14:45
 * @Description todo
 **/
@FeignClient(name = "sys-server")
public interface SysFeignClient {

    @GetMapping("/api/account/{username}")
    Account selectByUsername(@PathVariable("username") String username);

    @GetMapping("/api/client/clientId")
    Client selectByClientId(@RequestParam("clientId") String clientId);

}
