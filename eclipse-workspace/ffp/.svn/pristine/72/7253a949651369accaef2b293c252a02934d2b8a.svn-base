package com.forms.beneform4j.security.core.access.provider;

import java.util.Collection;

import com.forms.beneform4j.security.core.access.permission.IMenuPermission;
import com.forms.beneform4j.security.core.login.info.IAuthenticationInfo;
import com.forms.beneform4j.security.core.login.user.IUser;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 权限提供者接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-19<br>
 */
public interface IPermissionProvider {

    /**
     * 获取角色ID
     * 
     * @param user
     * @param info
     * @return
     */
    public Collection<Integer> getRoleIds(IUser user, IAuthenticationInfo info);

    /**
     * 获取权限ID
     * 
     * @param user
     * @param info
     * @return
     */
    public Collection<Integer> getPermissionIds(IUser user, IAuthenticationInfo info);

    /**
     * 获取菜单ID
     * 
     * @param user
     * @param info
     * @return
     */
    public Collection<IMenuPermission> getMenuPermissions(IUser user, IAuthenticationInfo info);
}
