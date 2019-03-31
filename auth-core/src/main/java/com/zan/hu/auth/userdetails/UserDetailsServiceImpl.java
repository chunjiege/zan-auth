package com.zan.hu.auth.userdetails;

import com.zan.hu.auth.domin.GlobalUser;
import com.zan.hu.auth.mapper.GlobalUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;


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
        return new SysUserDetails() {
            @Override
            public String getUserGuid() {
                return globalUser.getGuid();
            }

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return Arrays.asList(new SimpleGrantedAuthority(ROLE_USER));
            }

            @Override
            public String getPassword() {
                return null;
            }

            @Override
            public String getUsername() {
                return globalUser.getAccount();
            }

            @Override
            public boolean isAccountNonExpired() {
                return globalUser.getExpired();
            }

            @Override
            public boolean isAccountNonLocked() {
                return globalUser.getLocked();
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return globalUser.getExpired();
            }

            @Override
            public boolean isEnabled() {
                return globalUser.getEnabled();
            }
        };
    }
}
