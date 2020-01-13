package com.forms.beneform4j.security.core.access.info.impl;

import com.forms.beneform4j.security.core.access.info.IAuthorizationInfo;
import com.forms.beneform4j.security.core.access.permission.IPermission;
import com.forms.beneform4j.security.core.common.info.impl.BaseSecurityInfo;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 基本的授权信息实现类，默认授权成功，如果授权失败，需要设置返回码和描述<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-8<br>
 */
public class BaseAuthorizationInfo extends BaseSecurityInfo implements IAuthorizationInfo {

    /**
     * 权限信息
     */
    private IPermission permission;

    public BaseAuthorizationInfo() {
        super(true);
    }

    @Override
    public IPermission getPermission() {
        return this.permission;
    }

    @Override
    public void setPermission(IPermission permission) {
        this.permission = permission;
    }
}
