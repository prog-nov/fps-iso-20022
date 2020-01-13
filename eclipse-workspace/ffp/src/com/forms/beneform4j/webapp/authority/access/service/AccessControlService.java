package com.forms.beneform4j.webapp.authority.access.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.forms.beneform4j.core.service.request.IRequestInfo;
import com.forms.beneform4j.security.core.access.authz.IAuthorizer;
import com.forms.beneform4j.security.core.access.info.IAuthorizationInfo;
import com.forms.beneform4j.security.core.access.info.impl.BaseAuthorizationInfo;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 访问控制服务实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-11<br>
 */
public class AccessControlService implements IAccessControlService {

    /**
     * 权限认证器
     */
    @Autowired(required = false)
    private IAuthorizer authorizer;

    public IAuthorizer getAuthorizer() {
        return authorizer;
    }

    public void setAuthorizer(IAuthorizer authorizer) {
        this.authorizer = authorizer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IAuthorizationInfo isPermitted(IRequestInfo requestInfo) {
        IAuthorizer authorizer = getAuthorizer();
        if (null == authorizer) {// 如果没有授权器，认为授权成功
            IAuthorizationInfo info = new BaseAuthorizationInfo();
            info.setSuccess(true);
            return info;
        } else {
            return authorizer.isPermitted(requestInfo);
        }
    }
}
