package com.zan.hu.auth.userdetails;

import com.zan.hu.auth.feign.SysFeignClient;
import com.zan.hu.sys.GlobalUser;
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
    private SysFeignClient sysFeignClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        GlobalUser globalUser = sysFeignClient.selectByAccount(username);
        if (globalUser == null) {
            throw new UsernameNotFoundException(username);
        }
        SysAccount sysAccount = new SysAccount();
        BeanUtils.copyProperties(globalUser, sysAccount);
        sysAccount.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(ROLE_USER)));
        return sysAccount;
    }
}
