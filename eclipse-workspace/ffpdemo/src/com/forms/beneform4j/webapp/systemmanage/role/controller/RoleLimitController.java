package com.forms.beneform4j.webapp.systemmanage.role.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.web.annotation.JsonBody;
import com.forms.beneform4j.web.annotation.ListJsonBody;
import com.forms.beneform4j.web.annotation.PageJsonBody;
import com.forms.beneform4j.web.annotation.User;
import com.forms.beneform4j.webapp.systemmanage.role.bean.RoleBean;
import com.forms.beneform4j.webapp.systemmanage.role.bean.RoleLimitBean;
import com.forms.beneform4j.webapp.systemmanage.role.form.RoleLimitForm;
import com.forms.beneform4j.webapp.systemmanage.role.service.IRoleLimitService;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 角色约束管理控制层<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-4<br>
 */
@Controller
@Scope("request")
@RequestMapping("system/sysmanager/rolelimit")
public class RoleLimitController {

    @Resource(name = "roleLimitService")
    private IRoleLimitService service;

    /**
     * 列表
     * 
     * @param roleLimit
     * @param page
     * @return
     */
    @RequestMapping("list")
    @PageJsonBody
    public List<RoleLimitBean> list(RoleLimitForm roleLimit, IPage page) {
        return service.sList(roleLimit, page);
    }

    /**
     * 查找
     * 
     * @param limitNo
     * @return
     */
    @RequestMapping("find")
    @ListJsonBody
    public RoleLimitBean find(@RequestParam(name = "limitNo") String limitNo) {
        return service.sFind(limitNo);
    }

    /**
     * 列出角色
     * 
     * @param userId
     * @return
     */
    @RequestMapping("listRoleForAdd")
    @ListJsonBody
    public List<RoleBean> listRoleForAdd(@User String userId) {
        return service.sListRoleForAdd(userId);
    }

    /**
     * 列出角色权限
     * 
     * @param userId
     * @param limitNo
     * @return
     */
    @RequestMapping("listRoleForEdit")
    @ListJsonBody
    public List<RoleBean> listRolePermissionForEdit(@User String userId, @RequestParam(name = "limitNo") String limitNo) {
        return service.sListRoleForEdit(userId, limitNo);
    }

    /**
     * 新增
     * 
     * @param userId
     * @param roleLimit
     * @return
     */
    @RequestMapping("insert")
    @JsonBody
    public int insert(@User String userId, RoleLimitForm roleLimit) {
        roleLimit.setInstOper(userId);
        return service.sInsert(roleLimit);
    }

    /**
     * 更新
     * 
     * @param userId
     * @param roleLimit
     * @return
     */
    @RequestMapping("update")
    @JsonBody
    public int update(@User String userId, RoleLimitForm roleLimit) {
        roleLimit.setModiOper(userId);
        return service.sUpdate(roleLimit);
    }

    /**
     * 删除
     * 
     * @param limitNos
     * @return
     */
    @RequestMapping("delete")
    @JsonBody
    public int delete(@RequestParam(name = "limitNo[]") String[] limitNos) {
        return service.sDelete(limitNos);
    }
}
