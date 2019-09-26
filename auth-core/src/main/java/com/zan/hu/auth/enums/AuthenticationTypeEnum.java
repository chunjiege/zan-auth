package com.zan.hu.auth.enums;

import lombok.Getter;

import java.util.Objects;

@Getter
public enum AuthenticationTypeEnum {
    PWD("password", "账号密码登录"),
    SMS("sms", "短信登录");

    AuthenticationTypeEnum(String type, String description) {
        this.type = type;
        this.description = description;
    }

    private String type;

    private String description;


    public static AuthenticationTypeEnum getByType(String type) {
        for (AuthenticationTypeEnum authenticationTypeEnum : AuthenticationTypeEnum.values()) {
            if (Objects.equals(authenticationTypeEnum.getType(), type))
                return authenticationTypeEnum;
        }
        return null;
    }
}
