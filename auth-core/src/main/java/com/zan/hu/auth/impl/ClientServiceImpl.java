package com.zan.hu.auth.impl;

import com.zan.hu.auth.ClientService;
import com.zan.hu.auth.SysClient;
import com.zan.hu.auth.domin.Client;
import com.zan.hu.auth.mapper.ClientMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @Author admin
 * @Date 2019-04-04 13:23
 * @Description todo
 **/
@RestController
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void createClient(SysClient sysClient) {
        Client client = new Client();
        BeanUtils.copyProperties(sysClient, client);
        client.setClientSecret(passwordEncoder.encode(client.getClientSecret()));
        clientMapper.insertSelective(client);
    }
}
