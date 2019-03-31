package com.zan.hu.auth.impl;

import com.zan.hu.auth.domin.GlobalUser;
import com.zan.hu.auth.GlobalUserService;
import com.zan.hu.auth.SysGlobalUser;
import com.zan.hu.auth.mapper.GlobalUserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-03-31 00:28
 * @Description todo
 **/
@Service
public class GlobalUserServiceImpl implements GlobalUserService {

    @Autowired
    private GlobalUserMapper globalUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void register(SysGlobalUser sysGlobalUser) {
        GlobalUser globalUser = new GlobalUser();
        BeanUtils.copyProperties(sysGlobalUser, globalUser);
        globalUser.setGuid(sysGlobalUser.getGuid());
        globalUser.setPassword(passwordEncoder.encode(sysGlobalUser.getPassword()));
        globalUserMapper.insertSelective(globalUser);
        //todo 此时应该通过消息来通知user微服务create user
    }
}
