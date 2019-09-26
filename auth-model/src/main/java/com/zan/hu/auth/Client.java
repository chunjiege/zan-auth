package com.zan.hu.auth;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "`client`")
@Data
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
    private String autoApprove;

    private Boolean enabled;

    private Date created;

    private Date updated;

    private static final long serialVersionUID = 1L;


    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", clientId='" + clientId + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                ", resourceIds='" + resourceIds + '\'' +
                ", scope='" + scope + '\'' +
                ", authorizedGrantTypes='" + authorizedGrantTypes + '\'' +
                ", authorities='" + authorities + '\'' +
                ", accessTokenValidity=" + accessTokenValidity +
                ", refreshTokenValidity=" + refreshTokenValidity +
                ", autoApprove='" + autoApprove + '\'' +
                ", enabled=" + enabled +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}