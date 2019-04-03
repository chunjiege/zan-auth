package com.zan.hu.sample.mapper;

import com.zan.hu.auth.domin.Client;
import tk.mybatis.mapper.common.Mapper;

public interface ClientMapper extends Mapper<Client> {

    Client selectByClientId(String clientId);
}