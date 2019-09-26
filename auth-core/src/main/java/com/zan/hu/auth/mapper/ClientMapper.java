package com.zan.hu.auth.mapper;

import com.zan.hu.auth.Client;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import tk.mybatis.mapper.common.Mapper;

@CacheConfig(cacheNames = "client")
public interface ClientMapper extends Mapper<Client> {

    @Cacheable(key = "#p0")
    Client selectByClientId(String clientId);

    Client selectByClientIdOrClientSecret(@Param("clientId") String clientId, @Param("clientSecret") String clientSecret);
}