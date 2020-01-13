package com.forms.beneform4j.security.core.login.listener.impl;

import com.forms.beneform4j.security.core.login.info.IAuthenticationInfo;
import com.forms.beneform4j.security.core.login.listener.ILoginListener;
import com.forms.beneform4j.security.core.login.token.IAuthenticationToken;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 登录认证侦听器支持类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-8<br>
 */
public abstract class LoginListenerSupport implements ILoginListener {

    @Override
    public void beforeLogin(IAuthenticationToken authenticationToken) {

    }

    @Override
    public void onLoginSuccess(IAuthenticationToken authenticationToken, IAuthenticationInfo info) {

    }

    @Override
    public void onLoginFailure(IAuthenticationToken authenticationToken, IAuthenticationInfo info) {

    }

    @Override
    public void onLoginException(IAuthenticationToken authenticationToken, IAuthenticationInfo info, Exception exception) {

    }
}
