package com.forms.beneform4j.security.core.access.authz.impl;

import com.forms.beneform4j.core.service.request.IRequestInfo;
import com.forms.beneform4j.security.core.access.info.IAuthorizationInfo;
import com.forms.beneform4j.security.core.access.info.impl.BaseAuthorizationInfo;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 允许匹配的路径的授权器实现类，只要路径匹配，即授权成功<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-8<br>
 */
public class AllowPathMatcherAuthorizer extends AbstractPathMatcherAuthorizer {

    @Override
    protected IAuthorizationInfo doPermitted(IRequestInfo requestInfo) {
        IAuthorizationInfo info = new BaseAuthorizationInfo();
        info.setSuccess(true);
        return info;
    }
}
