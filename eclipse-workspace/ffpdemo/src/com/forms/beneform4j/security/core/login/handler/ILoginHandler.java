package com.forms.beneform4j.security.core.login.handler;

import com.forms.beneform4j.security.core.login.info.IAuthenticationInfo;
import com.forms.beneform4j.security.core.login.token.IAuthenticationToken;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 登录处理器接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-8<br>
 */
public interface ILoginHandler {

    /**
     * 登录处理，可以做登录校验、加载权限等处理，如果处理失败，需要将失败代码和描述填充至info对象中
     * 
     * @param authenticationToken 认证token
     * @param info 返回信息对象
     */
    public void handler(IAuthenticationToken authenticationToken, IAuthenticationInfo info);
}
