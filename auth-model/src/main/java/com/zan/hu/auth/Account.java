package com.zan.hu.auth;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Table(name = "`account`")
@Data
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
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
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户锁定
     */
    private Boolean locked;

    private Boolean enabled;

    /**
     * 用户过期
     */
    private Boolean expired;

    private Date created;

    private Date updated;


    public String getGUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }


    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", guid='" + guid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", locked=" + locked +
                ", enabled=" + enabled +
                ", expired=" + expired +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}