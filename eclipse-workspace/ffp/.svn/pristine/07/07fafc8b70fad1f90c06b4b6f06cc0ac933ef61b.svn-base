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
import com.forms.beneform4j.web.annotation.PageJsonBody;
import com.forms.beneform4j.web.annotation.TreeJsonBody;
import com.forms.beneform4j.web.annotation.User;
import com.forms.beneform4j.webapp.systemmanage.role.bean.RoleAllotBean;
import com.forms.beneform4j.webapp.systemmanage.role.bean.RolePermissionBean;
import com.forms.beneform4j.webapp.systemmanage.role.form.RoleAllotForm;
import com.forms.beneform4j.webapp.systemmanage.role.service.IRoleAllotService;
import com.forms.beneform4j.webapp.systemmanage.role.service.IRoleService;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 角色(分配）管理控制层<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-15<br>
 */
@Controller
@Scope("request")
@RequestMapping("system/sysmanager/roleallot")
public class RoleAllotController {

    @Resource(name = "roleService")
    private IRoleService roleService;

    @Resource(name = "roleAllotService")
    private IRoleAllotService service;

    /**
     * 列表
     * 
     * @param roleAllot
     * @param page
     * @return
     */
    @RequestMapping("list")
    @PageJsonBody
    public List<RoleAllotBean> list(RoleAllotForm roleAllot, IPage page) {
        return service.sList(roleAllot, page);
    }

    /**
     * 跳转到新增页面
     * 
     * @return
     */
    @RequestMapping("gotoAdd")
    public ModelAndView gotoAdd() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/sysmanager/roleallot/add");
        mv.addObject("permTypes", roleService.sListPermType(null));
        return mv;
    }

    /**
     * 跳转到编辑页面
     * 
     * @return
     */
    @RequestMapping("gotoEdit")
    public ModelAndView gotoEdit() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/sysmanager/roleallot/edit");
        mv.addObject("permTypes", roleService.sListPermType(null));
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
     * 新增
     * 
     * @param roleAllot
     * @param userId
     * @return
     */
    @RequestMapping("insert")
    @JsonBody
    public int insert(@Valid RoleAllotForm roleAllot, @User String userId) {
        roleAllot.setInstOper(userId);
        return service.sInsert(roleAllot);
    }

    /**
     * 编辑时根据用户列出权限
     * 
     * @param roleAllotId
     * @param userId
     * @param permType
     * @return
     */
    @RequestMapping("listRolePermissionForEdit")
    @TreeJsonBody
    public List<RolePermissionBean> listRolePermissionForEdit(@RequestParam(name = "roleAllotId") int roleAllotId, @User String userId, @RequestParam("permType") String permType) {
        return service.sListRolePermissionForEdit(userId, permType, roleAllotId);
    }

    /**
     * 更新
     * 
     * @param roleAllot
     * @param userId
     * @return
     */
    @RequestMapping("update")
    @JsonBody
    public int update(@Valid RoleAllotForm roleAllot, @User String userId) {
        roleAllot.setModiOper(userId);
        return service.sUpdate(roleAllot);
    }

    /**
     * 删除
     * 
     * @param roleAllotIds
     * @return
     */
    @RequestMapping("delete")
    @JsonBody
    public int delete(@RequestParam(name = "roleAllotId[]") int[] roleAllotIds) {
        return service.sDelete(roleAllotIds);
    }
}
