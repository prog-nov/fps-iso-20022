package com.forms.beneform4j.webapp.systemmanage.maintenance.service;

import java.util.List;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.webapp.systemmanage.maintenance.bean.MaintenanceBean;
import com.forms.beneform4j.webapp.systemmanage.maintenance.form.MaintenanceForm;
import com.forms.beneform4j.webapp.systemmanage.role.bean.RoleAllotBean;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 角色-角色(分配)关系维护<br>
 * Author : WangXiaoYing<br>
 * Version : 1.1.0 <br>
 * Since : 1.1.0 <br>
 * Date : 2017-3-13<br>
 */
public interface IMaintenanceService {

    /**
     * {@inheritDoc}
     */

    /**
     * 查询角色与角色(分配)列表
     * 
     * @param form
     * @param page
     * @return
     */
    public List<MaintenanceBean> sLoadMaintenance(MaintenanceForm form, IPage page);

    /**
     * 查询角色(分配)列表
     * 
     * @return
     */
    public List<MaintenanceBean> sAllotlist();

    /**
     * 查询下拉框无角色(分配)关系列表
     * 
     * @return
     */
    public List<MaintenanceBean> sAllotSelectlist();

    /**
     * 删除一组角色与角色(分配)的关系
     * 
     * @param roleIds
     * @return
     */
    int sAllotDelete(int[] roleIds);

    /**
     * 新增角色与角色(分配)的关系
     * 
     * @param form
     * @return
     */
    int sInsert(MaintenanceForm form);

    /**
     * 修改角色与角色(分配)的关系
     * 
     * @param form
     * @return
     */
    int sUpdate(MaintenanceForm form);

    public List<RoleAllotBean> sGetListRoleAllotForEdit(int currRoleId, int roleAllotId);
}
