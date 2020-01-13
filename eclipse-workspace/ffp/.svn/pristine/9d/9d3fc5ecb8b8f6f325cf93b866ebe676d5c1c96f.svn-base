package com.forms.beneform4j.security.core.login.listener.impl;

import com.forms.beneform4j.security.SecurityUtils;
import com.forms.beneform4j.security.core.login.info.IAuthenticationInfo;
import com.forms.beneform4j.security.core.login.token.IAuthenticationToken;
import com.forms.beneform4j.security.core.login.user.IUser;
import com.forms.beneform4j.security.core.session.ISession;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 创建登录Session的侦听器支持类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-19<br>
 */
public class SessionCreateListener extends LoginListenerSupport {

    @Override
    public void onLoginSuccess(IAuthenticationToken authenticationToken, IAuthenticationInfo info) {
        IUser user = info.getUser();
        if (null != user) {
            ISession session = SecurityUtils.getSessionManager().createSession();
            session.setUser(user);
        }
    }
}
