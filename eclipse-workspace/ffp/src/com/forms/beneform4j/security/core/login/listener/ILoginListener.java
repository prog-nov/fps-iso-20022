package com.forms.beneform4j.security.core.login.listener;

import com.forms.beneform4j.security.core.login.info.IAuthenticationInfo;
import com.forms.beneform4j.security.core.login.token.IAuthenticationToken;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 登录侦听器接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-8<br>
 */
public interface ILoginListener {

    /**
     * 登录认证之前触发
     * 
     * @param authenticationToken 认证token
     */
    public void beforeLogin(IAuthenticationToken authenticationToken);

    /**
     * 登录认证成功触发
     * 
     * @param authenticationToken 认证token
     * @param info 认证返回信息
     */
    public void onLoginSuccess(IAuthenticationToken authenticationToken, IAuthenticationInfo info);

    /**
     * 登录认证失败触发
     * 
     * @param authenticationToken 认证token
     * @param info 认证返回信息
     */
    public void onLoginFailure(IAuthenticationToken authenticationToken, IAuthenticationInfo info);

    /**
     * 登录认证过程中出现异常触发
     * 
     * @param authenticationToken 认证token
     * @param info 认证信息
     * @param exception 异常
     */
    public void onLoginException(IAuthenticationToken authenticationToken, IAuthenticationInfo info, Exception exception);

}
