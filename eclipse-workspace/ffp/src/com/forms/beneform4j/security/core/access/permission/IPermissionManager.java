package com.forms.beneform4j.security.core.access.permission;

import java.util.Collection;
import java.util.Set;

import com.forms.beneform4j.util.tree.ITree;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 权限管理接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-19<br>
 */
public interface IPermissionManager {

    /**
     * 获取所有角色ID
     * 
     * @return 所有角色ID
     */
    public Set<Integer> getRoleIds();

    /**
     * 添加角色ID
     * 
     * @param roleIds 角色ID
     */
    public void addRoleIds(Collection<Integer> roleIds);

    /**
     * 获取所有权限ID
     * 
     * @return 所有权限ID
     */
    public Set<Integer> getPermissionIds();

    /**
     * 添加权限ID
     * 
     * @param permissionIds 权限ID
     */
    public void addPermissionIds(Collection<Integer> permissionIds);

    /**
     * 添加菜单权限ID
     * 
     * @param menuPermissions 菜单权限ID
     */
    public void addMenuPermissions(Collection<? extends IMenuPermission> menuPermissions);

    /**
     * 获取菜单权限树
     * 
     * @return
     */
    public ITree<IMenuPermission> getMenuTree();
}
