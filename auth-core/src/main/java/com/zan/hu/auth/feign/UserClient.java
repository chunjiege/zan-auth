package com.zan.hu.auth.feign;

import com.zan.hu.auth.config.FeignConfiguration;
import com.zan.hu.user.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-06-03 10:23
 * @Description todo
 **/
@FeignClient(name = "user-server", configuration = FeignConfiguration.class)
public interface UserClient {

    @GetMapping("/api/user/{guid}")
    User selectByGuid(@PathVariable(value = "guid") String guid);
}
