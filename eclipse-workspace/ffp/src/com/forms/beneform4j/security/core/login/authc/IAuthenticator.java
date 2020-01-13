package com.forms.beneform4j.security.core.login.authc;

import com.forms.beneform4j.security.core.login.info.IAuthenticationInfo;
import com.forms.beneform4j.security.core.login.token.IAuthenticationToken;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 认证器接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-8<br>
 */
public interface IAuthenticator {

    /**
     * 登录认证
     * 
     * @param authenticationToken 认证token
     * @return 认证信息
     */
    public IAuthenticationInfo login(IAuthenticationToken authenticationToken);
}
