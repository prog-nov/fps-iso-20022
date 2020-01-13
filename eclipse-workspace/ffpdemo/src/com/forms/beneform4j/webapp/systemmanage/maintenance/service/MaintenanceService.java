package com.forms.beneform4j.webapp.systemmanage.maintenance.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.webapp.systemmanage.maintenance.bean.MaintenanceBean;
import com.forms.beneform4j.webapp.systemmanage.maintenance.dao.IMaintenanceDao;
import com.forms.beneform4j.webapp.systemmanage.maintenance.form.MaintenanceForm;
import com.forms.beneform4j.webapp.systemmanage.role.bean.RoleAllotBean;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 角色-角色(分配)关系维护<br>
 * Author : WangXiaoYing <br>
 * Version : 1.1.0 <br>
 * Since : 1.1.0 <br>
 * Date : 2017-3-13<br>
 */
@Service
public class MaintenanceService implements IMaintenanceService {

    @Resource
    private IMaintenanceDao dao;

    /**
     * 查询角色与角色(分配)关系列表
     */
    @Override
    public List<MaintenanceBean> sLoadMaintenance(MaintenanceForm form, IPage page) {

        return dao.dLoadMaintenance(form, page);
    }

    /**
     * 查找角色(分配)列表
     */
    @Override
    public List<MaintenanceBean> sAllotlist() {

        return dao.dAllotlist();
    }

    /**
     * 查找增加页面下拉框没有角色与角色(分配)关系的列表
     */
    @Override
    public List<MaintenanceBean> sAllotSelectlist() {

        return dao.dAllotSelectlist();
    }

    /**
     * 新增角色与角色(分配)的关系
     */
    @Override
    public int sInsert(MaintenanceForm form) {
        int[] rs = dao.dInsert(form);
        return rs[0];
    }

    /**
     * 修改角色与角色(分配)的关系
     */
    @Override
    public int sUpdate(MaintenanceForm form) {
        int[] rs = dao.dUpdate(form);
        return rs[0];
    }

    /**
     * 删除一组角色与角色分配的关系
     */
    @Override
    public int sAllotDelete(int[] roleIds) {
        int[] rs = dao.dDelete(roleIds);
        return rs[rs.length - 1];
    }

    /**
     * 修改勾选的角色与角色(分配)列表
     */
    @Override
    public List<RoleAllotBean> sGetListRoleAllotForEdit(int currRoleId, int roleAllotId) {
        return dao.dGetListRoleAllotForEdit(currRoleId, roleAllotId);
    }

}
