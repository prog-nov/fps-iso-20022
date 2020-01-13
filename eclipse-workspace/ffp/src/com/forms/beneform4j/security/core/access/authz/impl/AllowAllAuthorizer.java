package com.forms.beneform4j.security.core.access.authz.impl;

import com.forms.beneform4j.core.service.request.IRequestInfo;
import com.forms.beneform4j.security.core.access.authz.IAuthorizer;
import com.forms.beneform4j.security.core.access.info.IAuthorizationInfo;
import com.forms.beneform4j.security.core.access.info.impl.BaseAuthorizationInfo;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 允许所有访问的授权器实现类，可见即可得的权限控制<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-13<br>
 */
public class AllowAllAuthorizer implements IAuthorizer {

    @Override
    public IAuthorizationInfo isPermitted(IRequestInfo requestInfo) {
        IAuthorizationInfo info = new BaseAuthorizationInfo();
        info.setSuccess(true);
        return info;
    }
}
