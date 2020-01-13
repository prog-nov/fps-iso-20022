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
import com.forms.beneform4j.webapp.systemmanage.role.bean.RolePermissionBean;
import com.forms.beneform4j.webapp.systemmanage.role.form.RoleAllotForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 角色(分配)维护DAO<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-15<br>
 */
@Repository("roleAllotDao")
public interface IRoleAllotDao {

    /**
     * 查询角色(分配)列表
     * 
     * @param roleAllot
     * @param page
     * @return
     */
    public List<RoleAllotBean> dList(RoleAllotForm roleAllot, IPage page);

    /**
     * 查找单个角色(分配)
     * 
     * @param roleAllotId
     * @return
     */
    @SqlRef("dList")
    public RoleAllotBean dFind(@Param("roleAllotId") int roleAllotId);

    /**
     * 根据角色(分配)名称和需要排除的角色(分配)ID查找角色(分配)
     * 
     * @param roleAllotName
     * @param excludeRoleAllotId
     * @return
     */
    public List<RoleAllotBean> dListByRoleAllotName(@Param("roleAllotName") String roleAllotName, @Param("excludeRoleAllotId") int excludeRoleAllotId);

    /**
     * 新增角色(分配)时查询可维护的菜单列表
     * 
     * @param userId
     * @param permType
     * @return
     */
    public List<RolePermissionBean> dListRolePermissionForAdd(@Param("userId") String userId, @Param("permType") PermTypeBean permType);

    /**
     * 修改角色(分配)时查询可维护的菜单列表
     * 
     * @param userId
     * @param permType
     * @param roleAllotId
     * @return
     */
    public List<RolePermissionBean> dListRolePermissionForEdit(@Param("userId") String userId, @Param("permType") PermTypeBean permType, @Param("roleAllotId") int roleAllotId);

    /**
     * 新增单个角色(分配)
     * 
     * @param roleAllot
     * @return
     */
    @Executes({
            // 新增角色(分配)信息
            @Execute(sqlRef = @SqlRef("dInsertRoleAllot"), param = @BatchParam(false)),
            // 新增角色(分配)-权限关系
            @Execute(sqlRef = @SqlRef("dInsertRoleAllotPermission"), param = @BatchParam(item = "perm", property = "permissions"))})
    public int[] dInsert(RoleAllotForm roleAllot);

    /**
     * 更新单个角色(分配)、角色(分配)权限(菜单)关系
     * 
     * @param roleAllot
     * @return
     */
    @Executes({
            // 更新角色(分配)信息
            @Execute(sqlRef = @SqlRef("dUpdateRoleAllot"), param = @BatchParam(false)),
            // 删除角色(分配)权限关系
            @Execute(sqlRef = @SqlRef("dDeleteRoleAllotPermissionByPermTypes"), param = @BatchParam(value = false)),
            // 重新添加角色(分配)权限关系
            @Execute(sqlRef = @SqlRef("dInsertRoleAllotPermission"), param = @BatchParam(item = "perm", property = "permissions"))})
    public int[] dUpdate(RoleAllotForm roleAllot);

    /**
     * 删除一组角色(分配)
     * 
     * @param roleAllotIds
     * @return
     */
    @Executes({
            // 删除角色(分配)与角色关系
            @Execute(sqlRef = @SqlRef("dDeleteRoleRoleAllotByAllot"), param = @BatchParam(item = "roleAllotId")),
            // 删除角色(分配)与权限关系
            @Execute(sqlRef = @SqlRef("dDeleteRoleAllotPermission"), param = @BatchParam(item = "roleAllotId")),
            // 删除角色(分配)信息
            @Execute(sqlRef = @SqlRef("dDeleteRoleAllot"), param = @BatchParam(item = "roleAllotId"))})
    public int[] dDelete(int[] roleAllotIds);

}
