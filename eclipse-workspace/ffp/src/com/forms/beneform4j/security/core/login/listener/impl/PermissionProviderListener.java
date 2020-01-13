package com.forms.beneform4j.security.core.login.listener.impl;

import java.util.Collection;
import java.util.List;

import com.forms.beneform4j.security.core.access.permission.IMenuPermission;
import com.forms.beneform4j.security.core.access.permission.IPermissionManager;
import com.forms.beneform4j.security.core.access.provider.IPermissionProvider;
import com.forms.beneform4j.security.core.login.info.IAuthenticationInfo;
import com.forms.beneform4j.security.core.login.token.IAuthenticationToken;
import com.forms.beneform4j.security.core.login.user.IUser;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 添加权限的侦听器支持类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-10<br>
 */
public class PermissionProviderListener extends LoginListenerSupport {

    private List<IPermissionProvider> providers;

    @Override
    public void onLoginSuccess(IAuthenticationToken authenticationToken, IAuthenticationInfo info) {
        IUser user = info.getUser();
        List<IPermissionProvider> providers = getProviders();
        if (null != user && null != providers) {
            IPermissionManager pm = user.getPermissionManager();
            if (null != pm) {
                for (IPermissionProvider provider : providers) {
                    Collection<Integer> roleIds = provider.getRoleIds(user, info);
                    if (null != roleIds && !roleIds.isEmpty()) {
                        pm.addRoleIds(roleIds);
                    }
                    Collection<Integer> permissionIds = provider.getPermissionIds(user, info);
                    if (null != permissionIds && !permissionIds.isEmpty()) {
                        pm.addPermissionIds(permissionIds);
                    }
                    Collection<IMenuPermission> menuPermissionIds = provider.getMenuPermissions(user, info);
                    if (null != menuPermissionIds && !menuPermissionIds.isEmpty()) {
                        pm.addMenuPermissions(menuPermissionIds);
                    }
                }
            }
        }
    }

    public List<IPermissionProvider> getProviders() {
        return providers;
    }

    public void setProviders(List<IPermissionProvider> providers) {
        this.providers = providers;
    }
}
