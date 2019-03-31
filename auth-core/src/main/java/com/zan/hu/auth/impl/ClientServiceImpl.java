package com.zan.hu.auth.impl;

import com.zan.hu.auth.domin.Client;
import com.zan.hu.auth.ClientService;
import com.zan.hu.auth.SysClient;
import com.zan.hu.auth.mapper.ClientMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-03-31 00:34
 * @Description todo
 **/
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void create(SysClient sysClient) {
        Client client = new Client();
        BeanUtils.copyProperties(sysClient, client);
        String encode = passwordEncoder.encode(sysClient.getClientSecret());
        client.setClientSecret(encode);
        clientMapper.insertSelective(client);
    }
}
