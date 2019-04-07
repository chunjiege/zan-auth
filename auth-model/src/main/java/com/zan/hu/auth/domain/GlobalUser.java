package com.zan.hu.auth.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "`global_user`")
public class GlobalUser implements Serializable {
    /**
     * 用户 ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户全局 ID
     */
    private String guid;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * access_token
     */
    @Column(name = "access_token")
    private String accessToken;

    /**
     * refresh_token
     */
    @Column(name = "refresh_token")
    private String refreshToken;

    /**
     * expires_in
     */
    @Column(name = "expires_in")
    private Date expiresIn;

    /**
     * 用户锁定
     */
    private Boolean locked;

    private Boolean enabled;

    /**
     * 用户过期
     */
    private Boolean expired;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    private static final long serialVersionUID = 1L;

    /**
     * 获取用户 ID
     *
     * @return id - 用户 ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置用户 ID
     *
     * @param id 用户 ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户全局 ID
     *
     * @return guid - 用户全局 ID
     */
    public String getGuid() {
        return guid;
    }

    /**
     * 设置用户全局 ID
     *
     * @param guid 用户全局 ID
     */
    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }

    /**
     * 获取账号
     *
     * @return account - 账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置账号
     *
     * @param account 账号
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Date getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Date expiresIn) {
        this.expiresIn = expiresIn;
    }

    /**
     * 获取用户锁定
     *
     * @return locked - 用户锁定
     */
    public Boolean getLocked() {
        return locked;
    }

    /**
     * 设置用户锁定
     *
     * @param locked 用户锁定
     */
    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    /**
     * @return enabled
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * 获取用户过期
     *
     * @return expired - 用户过期
     */
    public Boolean getExpired() {
        return expired;
    }

    /**
     * 设置用户过期
     *
     * @param expired 用户过期
     */
    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    /**
     * @return created_at
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return updated_at
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}