package com.forms.beneform4j.security.core.access.provider.impl;

import java.util.Collection;

import com.forms.beneform4j.security.core.access.permission.IMenuPermission;
import com.forms.beneform4j.security.core.access.provider.IPermissionProvider;
import com.forms.beneform4j.security.core.login.info.IAuthenticationInfo;
import com.forms.beneform4j.security.core.login.user.IUser;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 权限提供者接口实现支持类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-19<br>
 */
public class PermissionProviderSupport implements IPermissionProvider {

    @Override
    public Collection<Integer> getRoleIds(IUser user, IAuthenticationInfo info) {
        return null;
    }

    @Override
    public Collection<Integer> getPermissionIds(IUser user, IAuthenticationInfo info) {
        return null;
    }

    @Override
    public Collection<IMenuPermission> getMenuPermissions(IUser user, IAuthenticationInfo info) {
        return null;
    }
}
