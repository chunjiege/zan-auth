package com.zan.hu.auth.service;

import com.zan.hu.auth.Client;
import com.zan.hu.auth.ClientService;
import com.zan.hu.auth.mapper.ClientMapper;
import com.zan.hu.auth.query.ClientInputDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @version 1.0
 * @Author admin
 * @Date 2019-04-04 13:23
 * @Description todo
 **/
@RestController
@Slf4j
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void create(ClientInputDTO clientInputDTO) throws Exception {
        Client client = clientMapper.selectByClientIdOrClientSecret(clientInputDTO.getClientId(), clientInputDTO.getClientSecret());
        if (client != null) {
            throw new Exception("");
        }
        client = new Client();
        BeanUtils.copyProperties(clientInputDTO, client);
        client.setClientSecret(passwordEncoder.encode(client.getClientSecret()));
        clientMapper.insertSelective(client);
    }

    @Override
    public void update(ClientInputDTO clientInputDTO) throws IllegalAccessException {
        Client client = clientMapper.selectByClientId(clientInputDTO.getClientId());
        if (client != null) {
//            String[] attributes = BeanComUtils.checkObjFieldIsNull(clientInputDTO);
//            client = client.doForward(clientInputDTO, client, attributes);
//            clientMapper.updateByPrimaryKeySelective(client);
        }
    }


    @Override
    public void delete(Long id) {
        clientMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Client> page(ClientInputDTO clientInputDTO) {
        Client clientPara = new Client();
        RowBounds rowBounds = new RowBounds();
        List<Client> clients = clientMapper.selectByExampleAndRowBounds(clientPara, rowBounds);
        return clients;
    }

    @Override
    public Client selectByClientId(String clientId) {
        Client client = clientMapper.selectByClientId(clientId);
        return client;
    }


}
