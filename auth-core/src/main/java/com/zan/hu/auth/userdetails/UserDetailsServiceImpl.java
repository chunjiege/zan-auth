package com.zan.hu.auth.userdetails;

import com.zan.hu.auth.domain.GlobalUser;
import com.zan.hu.auth.mapper.GlobalUserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;


/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-03-30 23:39
 * @Description todo
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final String ROLE_USER = "ROLE_USER";

    @Autowired
    private GlobalUserMapper globalUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        GlobalUser globalUser = globalUserMapper.selectByAccount(username);
        if (globalUser == null) {
            throw new UsernameNotFoundException(username);
        }
        SysAccount sysAccount = new SysAccount();
        BeanUtils.copyProperties(globalUser, sysAccount);
        sysAccount.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(ROLE_USER)));
        return sysAccount;
    }
}
