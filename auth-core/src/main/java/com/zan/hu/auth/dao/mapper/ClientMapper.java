package com.zan.hu.auth.dao.mapper;

import com.zan.hu.auth.domain.Client;
import tk.mybatis.mapper.common.Mapper;

public interface ClientMapper extends Mapper<Client> {

    Client selectByClientId(String clientId);
}