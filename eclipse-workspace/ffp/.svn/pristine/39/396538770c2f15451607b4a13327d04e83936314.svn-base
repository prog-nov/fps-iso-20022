package com.forms.beneform4j.webapp.systemmanage.maintenance.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.web.annotation.JsonBody;
import com.forms.beneform4j.web.annotation.ListJsonBody;
import com.forms.beneform4j.web.annotation.PageJsonBody;
import com.forms.beneform4j.webapp.systemmanage.maintenance.bean.MaintenanceBean;
import com.forms.beneform4j.webapp.systemmanage.maintenance.form.MaintenanceForm;
import com.forms.beneform4j.webapp.systemmanage.maintenance.service.IMaintenanceService;
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
@Controller
@RequestMapping("system/sysmanager/maintenance")
public class MaintenanceController {

    @Resource
    private IMaintenanceService service;

    /**
     * 加载角色与角色(分配)关系维护数据
     * 
     * @param form
     * @param page
     * @return
     */
    @RequestMapping("list")
    @PageJsonBody
    public List<MaintenanceBean> slist(MaintenanceForm form, IPage page) {
        List<MaintenanceBean> list = service.sLoadMaintenance(form, page);
        return list;
    }

    /**
     * 加载增加页面角色(分配)列表下拉框数据
     * 
     * @return
     */
    @RequestMapping("getListRoleAllotForSelect")
    @ListJsonBody
    public List<MaintenanceBean> sAllotSelectlist() {
        List<MaintenanceBean> list = service.sAllotSelectlist();
        return list;
    }

    /**
     * 加载增加页面角色与 角色(分配)列表数据
     * 
     * @return
     */
    @RequestMapping("getListRoleAllotForAdd")
    @ListJsonBody
    public List<MaintenanceBean> sAllotlist() {
        List<MaintenanceBean> list = service.sAllotlist();
        return list;
    }

    /**
     * 增加角色与角色(分配)的关系
     * 
     * @param form
     * @return
     */
    @RequestMapping("insert")
    @JsonBody
    public int insert(MaintenanceForm form) {
        return service.sInsert(form);
    }

    /**
     * 修改角色与角色(分配)的关系
     * 
     * @param form
     * @return
     */
    @RequestMapping("update")
    @JsonBody
    public int update(MaintenanceForm form) {
        return service.sUpdate(form);
    }

    /**
     * 删除角色与角色(分配)关系列表数据
     * 
     * @param roleIds
     * @return
     */
    @RequestMapping("delete")
    @JsonBody
    public int delete(@RequestParam(name = "roleId[]") int[] roleIds) {
        return service.sAllotDelete(roleIds);
    }

    /**
     * 加载修改角色与角色(分配)列表勾选项
     * 
     * @param roleId
     * @param roleAllotId
     * @return
     */
    @RequestMapping("getListRoleAllotForEdit")
    @ListJsonBody
    public List<RoleAllotBean> getListRoleAllotForEdit(@RequestParam(name = "roleId") int roleId, @RequestParam(name = "roleAllotId") int roleAllotId) {
        return service.sGetListRoleAllotForEdit(roleId, roleAllotId);
    }

}
