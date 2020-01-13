package com.forms.beneform4j.webapp.systemmanage.maintenance.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.forms.beneform4j.core.dao.annotation.BatchParam;
import com.forms.beneform4j.core.dao.annotation.Execute;
import com.forms.beneform4j.core.dao.annotation.Executes;
import com.forms.beneform4j.core.dao.annotation.SqlRef;
import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.webapp.systemmanage.maintenance.bean.MaintenanceBean;
import com.forms.beneform4j.webapp.systemmanage.maintenance.form.MaintenanceForm;
import com.forms.beneform4j.webapp.systemmanage.role.bean.RoleAllotBean;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 角色-角色(分配)关系维护<br>
 * Author : WangXiaoYng<br>
 * Version : 1.1.0 <br>
 * Since : 1.1.0 <br>
 * Date : 2017-3-13<br>
 */
@Repository("maintenanceDao")
public interface IMaintenanceDao {

    /**
     * 查询角色与角色(分配)关系列表
     * 
     * @param form
     * @param page
     * @return
     */
    public List<MaintenanceBean> dLoadMaintenance(MaintenanceForm form, IPage page);

    /**
     * 增加页面角色与角色(分配)关系列表
     * 
     * @return
     */
    public List<MaintenanceBean> dAllotlist();

    /**
     * 下拉框选择角色
     * 
     * @return
     */
    public List<MaintenanceBean> dAllotSelectlist();

    /**
     * 重新添加角色与角色(分配)的关系
     * 
     * @param form
     * @return
     */
    @Executes({@Execute(sqlRef = @SqlRef("dInsert"), param = @BatchParam(item = "role", property = "roles"))//
    })
    public int[] dInsert(MaintenanceForm form);

    /**
     * 修改角色与角色(分配)的关系
     * 
     * @param form
     * @return
     */
    @Executes({
            // 删除角色与角色(分配)的关系
            @Execute(sqlRef = @SqlRef("dDelete"), param = @BatchParam(value = false, property = "roleId")),
            // 重新添加角色与角色(分配)的关系
            @Execute(sqlRef = @SqlRef("dInsert"), param = @BatchParam(item = "role", property = "roles"))//
    })
    public int[] dUpdate(MaintenanceForm form);

    /**
     * 删除一组角色与角色(分配)关系
     * 
     * @param roleIds
     * @return
     */
    public int[] dDelete(@BatchParam(item = "roleId") int[] roleIds);

    /**
     * 修改时展示勾选角色与角色(分配)关
     * 
     * @param roleId
     * @param roleAllotId
     * @return
     */
    public List<RoleAllotBean> dGetListRoleAllotForEdit(@Param("roleId") int roleId, @Param("roleAllotId") int roleAllotId);
}
