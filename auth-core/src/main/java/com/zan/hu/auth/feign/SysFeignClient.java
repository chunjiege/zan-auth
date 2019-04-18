package com.zan.hu.auth.feign;

import com.zan.hu.sys.domain.Client;
import com.zan.hu.sys.domain.GlobalUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-04-10 14:45
 * @Description todo
 **/
@FeignClient(name = "sys-server")
public interface SysFeignClient {

    @GetMapping("/api/global/account")
    GlobalUser selectByAccount(@RequestParam("account") String account);

    @GetMapping("/api/client/clientId")
    Client selectByClientId(@RequestParam("clientId") String clientId);

}
