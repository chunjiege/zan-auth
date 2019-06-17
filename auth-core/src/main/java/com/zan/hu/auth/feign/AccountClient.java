package com.zan.hu.auth.feign;

import com.zan.hu.account.entity.Account;
import com.zan.hu.account.entity.Client;
import com.zan.hu.auth.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-04-10 14:45
 * @Description todo
 **/
@FeignClient(name = "account-server", configuration = FeignConfiguration.class)
public interface AccountClient {

    @GetMapping("/api/account/{username}")
    Account selectByUsername(@PathVariable("username") String username);

    @GetMapping("/api/client/{clientId}")
    Client selectByClientId(@PathVariable("clientId") String clientId);

}
