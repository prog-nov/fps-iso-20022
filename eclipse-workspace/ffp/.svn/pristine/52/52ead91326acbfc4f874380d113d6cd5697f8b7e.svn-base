package com.forms.beneform4j.webapp.systemmanage.role.service;

import java.util.List;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.webapp.systemmanage.role.bean.PermTypeBean;
import com.forms.beneform4j.webapp.systemmanage.role.bean.RoleAllotBean;
import com.forms.beneform4j.webapp.systemmanage.role.bean.RoleBean;
import com.forms.beneform4j.webapp.systemmanage.role.bean.RolePermissionBean;
import com.forms.beneform4j.webapp.systemmanage.role.form.RoleForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 角色维护服务接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-14<br>
 */
public interface IRoleService {

    /**
     * 查询角色列表
     * 
     * @param role
     * @param page
     * @return
     */
    List<RoleBean> sList(RoleForm role, IPage page);

    /**
     * 查询权限类型列表
     * 
     * @param permType 权限类型，permType为空时，查询所有权限类型列表
     * @return
     */
    List<PermTypeBean> sListPermType(String permType);

    /**
     * 查找单个角色
     * 
     * @param roleId
     * @return
     */
    RoleBean sFind(int roleId);

    /**
     * 新增角色时查询可维护的菜单列表
     * 
     * @param userId
     * @param permType
     * @return
     */
    List<RolePermissionBean> sListRolePermissionForAdd(String userId, String permType);

    /**
     * 修改角色时查询可维护的菜单列表
     * 
     * @param userId
     * @param permType
     * @param roleId
     * @return
     */
    List<RolePermissionBean> sListRolePermissionForEdit(String userId, String permType, int roleId);

    /**
     * 新增角色时查询可维护的角色(分配)列表
     * 
     * @param userId
     * @return
     */
    List<RoleAllotBean> sListRoleAllotForAdd(String userId);

    /**
     * 修改角色时查询可维护的角色(分配)列表
     * 
     * @param userId
     * @param roleId
     * @return
     */
    List<RoleAllotBean> sListRoleAllotForEdit(String userId, int roleId);

    /**
     * 新增单个角色
     * 
     * @param role
     * @return
     */
    int sInsert(RoleForm role);

    /**
     * 更新单个角色、角色权限(菜单)关系
     * 
     * @param role
     * @return
     */
    int sUpdate(RoleForm role);

    /**
     * 删除一组角色
     * 
     * @param roleIds
     * @return
     */
    int sDelete(int[] roleIds);
}
