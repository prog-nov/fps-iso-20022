package com.forms.beneform4j.security.core.access.listener;

import com.forms.beneform4j.core.service.request.IRequestInfo;
import com.forms.beneform4j.security.core.access.info.IAuthorizationInfo;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 访问控制侦听器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-8<br>
 */
public interface IAccessControlListener {

    /**
     * 授权前触发
     * 
     * @param requestInfo 请求信息
     */
    public void beforePermitted(IRequestInfo requestInfo);

    /**
     * 授权通过触发
     * 
     * @param requestInfo 请求信息
     * @param info 授权信息
     */
    public void onPermittedPass(IRequestInfo requestInfo, IAuthorizationInfo info);

    /**
     * 授权失败（拒绝访问）触发
     * 
     * @param requestInfo 请求信息
     * @param info 授权信息
     */
    public void onPermittedDeny(IRequestInfo requestInfo, IAuthorizationInfo info);

    /**
     * 授权时发生异常触发
     * 
     * @param requestInfo 请求信息
     * @param info 授权信息
     * @param exception 异常
     */
    public void onPermittedException(IRequestInfo requestInfo, IAuthorizationInfo info, Exception exception);
}
