package com.forms.beneform4j.webapp.authority.access.service.listener;

import com.forms.beneform4j.core.service.request.IRequestInfo;
import com.forms.beneform4j.security.core.access.info.IAuthorizationInfo;
import com.forms.beneform4j.security.core.access.listener.impl.AccessControlListenerSupport;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 写日志的访问侦听器接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-11<br>
 */
public class VisitLogListener extends AccessControlListenerSupport {

    /**
     * 授权通过时的日志
     */
    @Override
    public void onPermittedPass(IRequestInfo requestInfo, IAuthorizationInfo info) {
        super.onPermittedPass(requestInfo, info);
    }

    /**
     * 授权失败时的日志
     */
    @Override
    public void onPermittedDeny(IRequestInfo requestInfo, IAuthorizationInfo info) {

    }
}
