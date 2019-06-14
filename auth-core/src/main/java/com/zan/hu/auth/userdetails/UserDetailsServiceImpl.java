package com.zan.hu.auth.userdetails;

import com.zan.hu.account.entity.Account;
import com.zan.hu.auth.feign.AccountClient;
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
    private AccountClient accountClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountClient.selectByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException(username);
        }
        CurrentAccount currentAccount = new CurrentAccount();
        BeanUtils.copyProperties(account, currentAccount);
        currentAccount.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(ROLE_USER)));
        return currentAccount;
    }
}
