package com.forms.beneform4j.security.core.access.authz.impl;

import com.forms.beneform4j.core.service.request.IRequestInfo;
import com.forms.beneform4j.security.core.access.authz.IAuthorizer;
import com.forms.beneform4j.security.core.access.info.IAuthorizationInfo;
import com.forms.beneform4j.security.core.access.info.impl.BaseAuthorizationInfo;
import com.forms.beneform4j.security.core.common.pathmatcher.PathMatcherSupport;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 抽象的路径匹配授权器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-13<br>
 */
public abstract class AbstractPathMatcherAuthorizer extends PathMatcherSupport implements IAuthorizer {

    /**
     * 路径不匹配时是否通过授权验证
     */
    private boolean permittedWhenNotMatcher;

    @Override
    public IAuthorizationInfo isPermitted(IRequestInfo requestInfo) {
        if (super.isMatcher(requestInfo.getRequestUrl())) {
            return this.doPermitted(requestInfo);
        } else if (isPermittedWhenNotMatcher()) {
            IAuthorizationInfo info = new BaseAuthorizationInfo();
            info.setSuccess(true);
            return info;
        } else {
            return null;
        }
    }

    protected abstract IAuthorizationInfo doPermitted(IRequestInfo requestInfo);

    public boolean isPermittedWhenNotMatcher() {
        return permittedWhenNotMatcher;
    }

    public void setPermittedWhenUrlMatcher(boolean permittedWhenNotMatcher) {
        this.permittedWhenNotMatcher = permittedWhenNotMatcher;
    }
}
