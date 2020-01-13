package com.forms.beneform4j.security.core.login.token;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 登录认证Token接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-8<br>
 */
public interface IAuthenticationToken {

    /**
     * 获取认证主体，例如用户名
     * 
     * @return 认证主体
     */
    public Object getPrincipal();

    /**
     * 获取认证凭证，例如密码
     * 
     * @return 认证凭证
     */
    public Object getCredentials();

}
