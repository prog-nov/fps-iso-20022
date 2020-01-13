package com.forms.beneform4j.security.core.login.token.impl;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.forms.beneform4j.security.core.login.token.IAuthenticationToken;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 用户名密码认证Token<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-8<br>
 */
public class UsernamePasswordToken implements IAuthenticationToken {

    /**
     * 用户Id
     */
    @NotBlank(message = "{NotBlank.usernamePasswordToken.userId}")
    private String userId;

    /**
     * 用户密码
     */
    @Size(min = 6, max = 16, message = "{Size.usernamePasswordToken.userPwd}")
    private String userPwd;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    @Override
    public Object getPrincipal() {
        return getUserId();
    }

    @Override
    public Object getCredentials() {
        return getUserPwd();
    }
}
