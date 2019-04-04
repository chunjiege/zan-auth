package com.zan.hu.auth.userdetails;

import com.zan.hu.auth.domin.GlobalUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-04-01 20:09
 * @Description 如果使用Redis存储token，UserDetailsService接口直接返回SysAccountDetails，会有序列化异常，应该需要一个类来
 * 实现Serializable
 **/
@Data
public class SysAccount extends GlobalUser implements SysAccountDetails, Serializable {

    private List<SimpleGrantedAuthority> authorities;

    public SysAccount() {
        super();
    }

    @Override
    public String getUserGuid() {
        return super.getGuid();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getAccount();
    }

    @Override
    public boolean isAccountNonExpired() {
        return super.getExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return super.getLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return super.getExpired();
    }

    @Override
    public boolean isEnabled() {
        return super.getEnabled();
    }
}
