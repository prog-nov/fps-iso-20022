package com.forms.beneform4j.webapp.systemmanage.role.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.forms.beneform4j.core.dao.annotation.BatchParam;
import com.forms.beneform4j.core.dao.annotation.Execute;
import com.forms.beneform4j.core.dao.annotation.Executes;
import com.forms.beneform4j.core.dao.annotation.SqlRef;
import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.webapp.systemmanage.role.bean.PermTypeBean;
import com.forms.beneform4j.webapp.systemmanage.role.bean.RoleAllotBean;
import com.forms.beneform4j.webapp.systemmanage.role.bean.RoleBean;
import com.forms.beneform4j.webapp.systemmanage.role.bean.RolePermissionBean;
import com.forms.beneform4j.webapp.systemmanage.role.form.RoleForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 角色维护DAO<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-14<br>
 */
@Repository("roleDao")
public interface IRoleDao {

    /**
     * 查询角色列表
     * 
     * @param role
     * @param page
     * @return
     */
    public List<RoleBean> dList(RoleForm role, IPage page);

    /**
     * 查找单个角色
     * 
     * @param roleId
     * @return
     */
    @SqlRef("dList")
    public RoleBean dFind(@Param("roleId") int roleId);

    /**
     * 查询权限类型列表
     * 
     * @param permType
     * @return
     */
    public List<PermTypeBean> dListPermType(@Param("permType") String permType);

    /**
     * 根据角色名称和需要排除的角色ID查找角色
     * 
     * @param roleName
     * @param excludeRoleId
     * @return
     */
    public List<RoleBean> dListByRoleName(@Param("roleName") String roleName, @Param("excludeRoleId") int excludeRoleId);

    /**
     * 新增角色时查询可维护的菜单列表
     * 
     * @param userId
     * @param permType
     * @return
     */
    public List<RolePermissionBean> dListRolePermissionForAdd(@Param("userId") String userId, @Param("permType") PermTypeBean permType);

    /**
     * 修改角色时查询可维护的菜单列表
     * 
     * @param userId
     * @param permType
     * @param roleId
     * @return
     */
    public List<RolePermissionBean> dListRolePermissionForEdit(@Param("userId") String userId, @Param("permType") PermTypeBean permType, @Param("roleId") int roleId);

    /**
     * 新增角色时查询可维护的角色(分配)列表
     * 
     * @param userId
     * @return
     */
    public List<RoleAllotBean> dListRoleAllotForAdd(@Param("userId") String userId);

    /**
     * 修改角色时查询可维护的角色(分配)列表
     * 
     * @param userId
     * @param roleId
     * @return
     */
    public List<RoleAllotBean> dListRoleAllotForEdit(@Param("userId") String userId, @Param("roleId") int roleId);

    /**
     * 新增单个角色
     * 
     * @param role
     * @return
     */
    @Executes({
            // 新增角色信息
            @Execute(sqlRef = @SqlRef("dInsertRole"), param = @BatchParam(false)),
            // 新增角色-权限关系
            @Execute(sqlRef = @SqlRef("dInsertRolePermission"), param = @BatchParam(item = "perm", property = "permissions")),
            // 新增角色-角色(分配)关系
            @Execute(sqlRef = @SqlRef("dInsertRoleRoleAllot"), param = @BatchParam(item = "allot", property = "roleAllots"))})
    public int[] dInsert(RoleForm role);

    /**
     * 更新单个角色、角色权限(菜单)关系
     * 
     * @param role
     * @return
     */
    @Executes({
            // 更新角色信息
            @Execute(sqlRef = @SqlRef("dUpdateRole"), param = @BatchParam(false)),
            // 删除角色权限关系
            @Execute(sqlRef = @SqlRef("dDeleteRolePermissionByPermTypes"), param = @BatchParam(value = false)),
            // 重新添加角色权限关系
            @Execute(sqlRef = @SqlRef("dInsertRolePermission"), param = @BatchParam(item = "perm", property = "permissions")),
            // 删除角色与角色(分配)关系
            @Execute(sqlRef = @SqlRef("dDeleteRoleRoleAllot"), param = @BatchParam(value = false), condition = "roleAllotLoaded"),
            // 新增角色-角色(分配)关系
            @Execute(sqlRef = @SqlRef("dInsertRoleRoleAllot"), param = @BatchParam(item = "allot", property = "roleAllots"))})
    public int[] dUpdate(RoleForm role);

    /**
     * 删除一组角色
     * 
     * @param roleIds
     * @return
     */
    @Executes({
            // 删除角色约束关系
            @Execute(sqlRef = @SqlRef("dDeleteRoleLimit"), param = @BatchParam(item = "roleId")),
            // 删除角色与角色(分配)关系
            @Execute(sqlRef = @SqlRef("dDeleteRoleRoleAllot"), param = @BatchParam(item = "roleId")),
            // 删除角色与权限关系
            @Execute(sqlRef = @SqlRef("dDeleteRolePermission"), param = @BatchParam(item = "roleId")),
            // 删除角色与用户关系
            @Execute(sqlRef = @SqlRef("dDeleteUserRole"), param = @BatchParam(item = "roleId")),
            // 删除角色信息
            @Execute(sqlRef = @SqlRef("dDeleteRole"), param = @BatchParam(item = "roleId"))})
    public int[] dDelete(int[] roleIds);

}
