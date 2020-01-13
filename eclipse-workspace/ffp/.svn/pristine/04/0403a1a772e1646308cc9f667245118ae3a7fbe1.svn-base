package com.forms.beneform4j.security.core.login.handler.impl;

import com.forms.beneform4j.security.core.login.handler.ILoginHandler;
import com.forms.beneform4j.security.core.login.info.IAuthenticationInfo;
import com.forms.beneform4j.security.core.login.token.IAuthenticationToken;
import com.forms.beneform4j.security.core.login.token.impl.UsernamePasswordToken;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 使用用户名密码token的处理器接口抽象实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-8<br>
 */
public abstract class UsernamePasswordTokenHandler implements ILoginHandler {

    @Override
    public void handler(IAuthenticationToken authenticationToken, IAuthenticationInfo info) {
        if (authenticationToken instanceof UsernamePasswordToken) {
            UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
            this.doHandler(token, info);
        }
    }

    protected abstract void doHandler(UsernamePasswordToken token, IAuthenticationInfo info);
}
