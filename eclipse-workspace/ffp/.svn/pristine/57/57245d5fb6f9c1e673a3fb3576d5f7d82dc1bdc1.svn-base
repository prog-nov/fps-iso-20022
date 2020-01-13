package com.forms.beneform4j.webapp.systemmanage.user.controller;

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
import com.forms.beneform4j.webapp.systemmanage.user.bean.UserBean;
import com.forms.beneform4j.webapp.systemmanage.user.form.UserForm;
import com.forms.beneform4j.webapp.systemmanage.user.service.IUserService;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 用户控制层<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-11<br>
 */
@Controller
@Scope("request")
@RequestMapping("system/sysmanager/user")
public class UserController {

    @Resource(name = "userService")
    private IUserService service;

    /**
     * 列表
     * 
     * @param user
     * @param page
     * @return
     */
    @RequestMapping("list")
    @PageJsonBody
    public List<UserBean> list(UserForm user, IPage page) {
        return service.sList(user, page);
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
     * 新增
     * 
     * @param user
     * @param userId
     * @return
     */
    @RequestMapping("insert")
    @JsonBody
    public int insert(UserForm user, @User String userId) {
        user.setInstOper(userId);
        return service.sInsert(user);
    }

    /**
     * 编辑页面角色获取
     * 
     * @param currUserId
     * @param userId
     * @return
     */
    @RequestMapping("listRoleForEdit")
    @ListJsonBody
    public List<RoleBean> listRolePermissionForEdit(@User String currUserId, @RequestParam(name = "userId") String userId) {
        return service.sListRoleForEdit(currUserId, userId);
    }

    /**
     * 修改
     * 
     * @param user
     * @param userId
     * @return
     */
    @RequestMapping("update")
    @JsonBody
    public int update(UserForm user, @User String userId) {
        user.setModiOper(userId);
        return service.sUpdate(user);
    }

    /**
     * 删除
     * 
     * @param userIds
     * @return
     */
    @RequestMapping("delete")
    @JsonBody
    public int delete(@RequestParam(name = "userId[]") String[] userIds,@User String operId) {
        return service.sDelete(userIds,operId);
    }

    /**
     * 重置密码
     * 
     * @param userId
     * @return
     */
    @RequestMapping("resetPassword")
    @JsonBody
    public int resetPassword(@RequestParam("userId") String userId) {
        return service.sResetPassword(userId);
    }

    /**
     * 更新用户状态
     * 
     * @param userId
     * @param userStatus
     * @return
     */
    @RequestMapping("updateUserStatus")
    @JsonBody
    public int updateUserStatus(@RequestParam("userId") String userId,@User String operId, @RequestParam("userStatus") boolean userStatus) {
        return service.sUpdateUserStatus(userId, operId,userStatus);
    }

    /**
     * 更新用户锁定状态
     * 
     * @param userId
     * @param lockFlag
     * @return
     */
    @RequestMapping("updateLockFlag")
    @JsonBody
    public int updateLockFlag(@RequestParam("userId") String userId, @RequestParam("lockFlag") boolean lockFlag) {
        return service.sUpdateLockFlag(userId, lockFlag);
    }

    /**
     * 强制下线
     * 
     * @param userId
     * @return
     */
    @RequestMapping("offline")
    @JsonBody
    public int offline(@RequestParam("userId") String userId) {
        return service.sOffline(userId);
    }
}
