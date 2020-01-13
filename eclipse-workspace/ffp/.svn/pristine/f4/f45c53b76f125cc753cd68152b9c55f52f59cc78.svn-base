package com.forms.beneform4j.security.core.access.authz.impl;

import java.util.List;

import com.forms.beneform4j.core.service.request.IRequestInfo;
import com.forms.beneform4j.security.core.access.authz.IAuthorizer;
import com.forms.beneform4j.security.core.access.info.IAuthorizationInfo;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 授权器复合类，只要其中一个授权成功即可<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-8<br>
 */
public class AuthorizerComposite implements IAuthorizer {

    private List<IAuthorizer> authorizers;

    @Override
    public IAuthorizationInfo isPermitted(IRequestInfo requestInfo) {
        List<IAuthorizer> authorizers = getAuthorizers();
        if (null != authorizers && !authorizers.isEmpty()) {
            for (IAuthorizer authorizer : authorizers) {
                IAuthorizationInfo info = authorizer.isPermitted(requestInfo);
                if (null != info && info.isSuccess()) {// 只要一个成功就返回
                    return info;
                }
            }
        }
        return null;
    }

    public List<IAuthorizer> getAuthorizers() {
        return authorizers;
    }

    public void setAuthorizers(List<IAuthorizer> authorizers) {
        this.authorizers = authorizers;
    }
}
