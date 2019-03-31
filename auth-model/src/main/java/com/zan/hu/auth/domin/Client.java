package com.zan.hu.auth.domin;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "`client`")
public class Client implements Serializable {
    /**
     * 客户端 ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 客户端 ID
     */
    @Column(name = "client_id")
    private String clientId;

    @Column(name = "client_secret")
    private String clientSecret;

    /**
     * 资源 ID 集合 逗号分隔
     */
    @Column(name = "resource_ids")
    private String resourceIds;

    /**
     * 操作范围，read,write
     */
    private String scope;

    /**
     * 授权类型
     */
    @Column(name = "authorized_grant_types")
    private String authorizedGrantTypes;

    /**
     * 默认权限集合，逗号分隔
     */
    private String authorities;

    /**
     * AccessToken 有效期，单位：秒
     */
    @Column(name = "access_token_validity")
    private Integer accessTokenValidity;

    /**
     * RefreshToken 有效期，默认单位：秒
     */
    @Column(name = "refresh_token_validity")
    private Integer refreshTokenValidity;

    /**
     * 自动授予
     */
    private String autoapprove;

    private Boolean enabled;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    private static final long serialVersionUID = 1L;

    /**
     * 获取客户端 ID
     *
     * @return id - 客户端 ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置客户端 ID
     *
     * @param id 客户端 ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取客户端 ID
     *
     * @return client_id - 客户端 ID
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * 设置客户端 ID
     *
     * @param clientId 客户端 ID
     */
    public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }

    /**
     * @return client_secret
     */
    public String getClientSecret() {
        return clientSecret;
    }

    /**
     * @param clientSecret
     */
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret == null ? null : clientSecret.trim();
    }

    /**
     * 获取资源 ID 集合 逗号分隔
     *
     * @return resource_ids - 资源 ID 集合 逗号分隔
     */
    public String getResourceIds() {
        return resourceIds;
    }

    /**
     * 设置资源 ID 集合 逗号分隔
     *
     * @param resourceIds 资源 ID 集合 逗号分隔
     */
    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds == null ? null : resourceIds.trim();
    }

    /**
     * 获取操作范围，read,write
     *
     * @return scope - 操作范围，read,write
     */
    public String getScope() {
        return scope;
    }

    /**
     * 设置操作范围，read,write
     *
     * @param scope 操作范围，read,write
     */
    public void setScope(String scope) {
        this.scope = scope == null ? null : scope.trim();
    }

    /**
     * 获取授权类型
     *
     * @return authorized_grant_types - 授权类型
     */
    public String getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    /**
     * 设置授权类型
     *
     * @param authorizedGrantTypes 授权类型
     */
    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes == null ? null : authorizedGrantTypes.trim();
    }

    /**
     * 获取默认权限集合，逗号分隔
     *
     * @return authorities - 默认权限集合，逗号分隔
     */
    public String getAuthorities() {
        return authorities;
    }

    /**
     * 设置默认权限集合，逗号分隔
     *
     * @param authorities 默认权限集合，逗号分隔
     */
    public void setAuthorities(String authorities) {
        this.authorities = authorities == null ? null : authorities.trim();
    }

    /**
     * 获取AccessToken 有效期，单位：秒
     *
     * @return access_token_validity - AccessToken 有效期，单位：秒
     */
    public Integer getAccessTokenValidity() {
        return accessTokenValidity;
    }

    /**
     * 设置AccessToken 有效期，单位：秒
     *
     * @param accessTokenValidity AccessToken 有效期，单位：秒
     */
    public void setAccessTokenValidity(Integer accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    /**
     * 获取RefreshToken 有效期，默认单位：秒
     *
     * @return refresh_token_validity - RefreshToken 有效期，默认单位：秒
     */
    public Integer getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    /**
     * 设置RefreshToken 有效期，默认单位：秒
     *
     * @param refreshTokenValidity RefreshToken 有效期，默认单位：秒
     */
    public void setRefreshTokenValidity(Integer refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    /**
     * 获取自动授予
     *
     * @return autoapprove - 自动授予
     */
    public String getAutoapprove() {
        return autoapprove;
    }

    /**
     * 设置自动授予
     *
     * @param autoapprove 自动授予
     */
    public void setAutoapprove(String autoapprove) {
        this.autoapprove = autoapprove == null ? null : autoapprove.trim();
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