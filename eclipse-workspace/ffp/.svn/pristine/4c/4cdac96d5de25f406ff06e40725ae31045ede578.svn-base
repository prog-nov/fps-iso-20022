package com.forms.beneform4j.webapp.systemmanage.role.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.web.annotation.JsonBody;
import com.forms.beneform4j.web.annotation.ListJsonBody;
import com.forms.beneform4j.web.annotation.PageJsonBody;
import com.forms.beneform4j.web.annotation.TreeJsonBody;
import com.forms.beneform4j.web.annotation.User;
import com.forms.beneform4j.webapp.systemmanage.role.bean.RoleAllotBean;
import com.forms.beneform4j.webapp.systemmanage.role.bean.RoleBean;
import com.forms.beneform4j.webapp.systemmanage.role.bean.RolePermissionBean;
import com.forms.beneform4j.webapp.systemmanage.role.form.RoleForm;
import com.forms.beneform4j.webapp.systemmanage.role.service.IRoleService;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 角色管理控制层<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-3<br>
 */
@Controller
@Scope("request")
@RequestMapping("system/sysmanager/role")
public class RoleController {

    @Resource(name = "roleService")
    private IRoleService service;

    /**
     * 列表
     * 
     * @param role
     * @param page
     * @return
     */
    @RequestMapping("list")
    @PageJsonBody
    public List<RoleBean> list(RoleForm role, IPage page) {
        return service.sList(role, page);
    }

    /**
     * 新增页面
     * 
     * @return
     */
    @RequestMapping("gotoAdd")
    public ModelAndView gotoAdd() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/sysmanager/role/add");
        mv.addObject("permTypes", service.sListPermType(null));
        return mv;
    }

    /**
     * 编辑页面
     * 
     * @return
     */
    @RequestMapping("gotoEdit")
    public ModelAndView gotoEdit() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/sysmanager/role/edit");
        mv.addObject("permTypes", service.sListPermType(null));
        return mv;
    }

    /**
     * 新增时根据用户列出权限
     * 
     * @param userId
     * @param permType
     * @return
     */
    @RequestMapping("listRolePermissionForAdd")
    @TreeJsonBody
    public List<RolePermissionBean> listRolePermissionForAdd(@User String userId, @RequestParam("permType") String permType) {
        return service.sListRolePermissionForAdd(userId, permType);
    }

    /**
     * 根据用户列出权限
     * 
     * @param userId
     * @return
     */
    @RequestMapping("listRoleAllotForAdd")
    @ListJsonBody
    public List<RoleAllotBean> listRoleAllotForAdd(@User String userId) {
        return service.sListRoleAllotForAdd(userId);
    }

    /**
     * 新增
     * 
     * @param role
     * @param userId
     * @return
     */
    @RequestMapping("insert")
    @JsonBody
    public int insert(@Valid RoleForm role, @User String userId) {
        role.setInstOper(userId);
        return service.sInsert(role);
    }

    /**
     * 列出权限
     * 
     * @param roleId
     * @param userId
     * @param permType
     * @return
     */
    @RequestMapping("listRolePermissionForEdit")
    @TreeJsonBody
    public List<RolePermissionBean> listRolePermissionForEdit(@RequestParam(name = "roleId") int roleId, @User String userId, @RequestParam("permType") String permType) {
        return service.sListRolePermissionForEdit(userId, permType, roleId);
    }

    /**
     * 列出权限
     * 
     * @param roleId
     * @param userId
     * @return
     */
    @RequestMapping("listRoleAllotForEdit")
    @ListJsonBody
    public List<RoleAllotBean> listRoleAllotForEdit(@RequestParam(name = "roleId") int roleId, @User String userId) {
        return service.sListRoleAllotForEdit(userId, roleId);
    }

    /**
     * 更新
     * 
     * @param role
     * @param userId
     * @return
     */
    @RequestMapping("update")
    @JsonBody
    public int update(@Valid RoleForm role, @User String userId) {
        role.setModiOper(userId);
        return service.sUpdate(role);
    }

    /**
     * 删除
     * 
     * @param roleIds
     * @return
     */
    @RequestMapping("delete")
    @JsonBody
    public int delete(@RequestParam(name = "roleId[]") int[] roleIds) {
        return service.sDelete(roleIds);
    }
}
