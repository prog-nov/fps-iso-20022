package com.forms.beneform4j.security.core.login.authc.impl;

import java.util.List;

import com.forms.beneform4j.security.core.login.authc.IAuthenticator;
import com.forms.beneform4j.security.core.login.info.IAuthenticationInfo;
import com.forms.beneform4j.security.core.login.token.IAuthenticationToken;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 复合认证器实现类，其中一个认证成功即为成功<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-8<br>
 */
public class AuthenticatorComposite implements IAuthenticator {

    private List<IAuthenticator> authenticators;

    @Override
    public IAuthenticationInfo login(IAuthenticationToken authenticationToken) {
        List<IAuthenticator> authenticators = getAuthenticators();
        if (null != authenticators && !authenticators.isEmpty()) {
            for (IAuthenticator authenticator : authenticators) {
                IAuthenticationInfo info = authenticator.login(authenticationToken);
                if (null != info && info.isSuccess()) {
                    return info;
                }
            }
        }
        return null;
    }

    public List<IAuthenticator> getAuthenticators() {
        return authenticators;
    }

    public void setAuthenticators(List<IAuthenticator> authenticators) {
        this.authenticators = authenticators;
    }
}
