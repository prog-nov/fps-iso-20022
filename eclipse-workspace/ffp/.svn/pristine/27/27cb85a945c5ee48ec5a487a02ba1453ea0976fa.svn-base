package com.forms.beneform4j.webapp.systemmanage.role.service;

import java.util.List;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.webapp.systemmanage.role.bean.RoleAllotBean;
import com.forms.beneform4j.webapp.systemmanage.role.bean.RolePermissionBean;
import com.forms.beneform4j.webapp.systemmanage.role.form.RoleAllotForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 角色(分配)维护服务接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-15<br>
 */
public interface IRoleAllotService {

    /**
     * 查询角色(分配)列表
     * 
     * @param roleAllot
     * @param page
     * @return
     */
    List<RoleAllotBean> sList(RoleAllotForm roleAllot, IPage page);

    /**
     * 查找单个角色(分配)
     * 
     * @param roleAllotId
     * @return
     */
    RoleAllotBean sFind(int roleAllotId);

    /**
     * 新增角色(分配)时查询可维护的菜单列表
     * 
     * @param userId
     * @param permType
     * @return
     */
    List<RolePermissionBean> sListRolePermissionForAdd(String userId, String permType);

    /**
     * 修改角色(分配)时查询可维护的菜单列表
     * 
     * @param userId
     * @param permType
     * @param roleAllotId
     * @return
     */
    List<RolePermissionBean> sListRolePermissionForEdit(String userId, String permType, int roleAllotId);

    /**
     * 新增单个角色(分配)
     * 
     * @param roleAllot
     * @return
     */
    int sInsert(RoleAllotForm roleAllot);

    /**
     * 更新单个角色(分配)、角色(分配)权限(菜单)关系
     * 
     * @param roleAllot
     * @return
     */
    int sUpdate(RoleAllotForm roleAllot);

    /**
     * 删除一组角色(分配)
     * 
     * @param roleAllotIds
     * @return
     */
    int sDelete(int[] roleAllotIds);
}
