package com.forms.beneform4j.security.core.access.listener.impl;

import com.forms.beneform4j.core.service.request.IRequestInfo;
import com.forms.beneform4j.security.core.access.info.IAuthorizationInfo;
import com.forms.beneform4j.security.core.access.listener.IAccessControlListener;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 访问控制侦听器支持类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-8<br>
 */
public abstract class AccessControlListenerSupport implements IAccessControlListener {

    @Override
    public void beforePermitted(IRequestInfo requestInfo) {

    }

    @Override
    public void onPermittedPass(IRequestInfo requestInfo, IAuthorizationInfo info) {

    }

    @Override
    public void onPermittedDeny(IRequestInfo requestInfo, IAuthorizationInfo info) {

    }

    @Override
    public void onPermittedException(IRequestInfo requestInfo, IAuthorizationInfo info, Exception exception) {

    }
}
