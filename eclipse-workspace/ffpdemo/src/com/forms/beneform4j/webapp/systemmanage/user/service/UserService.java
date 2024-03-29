package com.forms.beneform4j.webapp.systemmanage.user.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.webapp.common.exception.ExceptionCodes;
import com.forms.beneform4j.webapp.systemmanage.role.bean.RoleBean;
import com.forms.beneform4j.webapp.systemmanage.role.bean.RoleLimitBean;
import com.forms.beneform4j.webapp.systemmanage.role.form.RoleForm;
import com.forms.beneform4j.webapp.systemmanage.role.service.IRoleLimitService;
import com.forms.beneform4j.webapp.systemmanage.user.bean.UserBean;
import com.forms.beneform4j.webapp.systemmanage.user.dao.IUserDao;
import com.forms.beneform4j.webapp.systemmanage.user.form.UserForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 用户管理模块服务层<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-11<br>
 */
@Service("userService")
@Scope("prototype")
public class UserService implements IUserService {

    @Resource(name = "userDao")
    private IUserDao dao;

    @Resource(name = "roleLimitService")
    private IRoleLimitService roleLimitService;

    /**
     * 查询用户列表
     * 
     * @param user
     * @param page
     * @return
     */
    @Override
    public List<UserBean> sList(UserForm user, IPage page) {
        return dao.dList(user, page);
    }

    /**
     * 查找单个用户
     * 
     * @param userId
     * @return
     */
    @Override
    public UserBean sFind(String userId) {
        return dao.dFind(userId);
    }

    /**
     * 新增用户时查询可维护的角色列表
     * 
     * @param userId
     * @return
     */
    @Override
    public List<RoleBean> sListRoleForAdd(String userId) {
        return dao.dListRoleForAdd(userId);
    }

    /**
     * 修改用户时查询可维护的角色列表
     * 
     * @param userId
     * @param editedUserId
     * @return
     */
    @Override
    public List<RoleBean> sListRoleForEdit(String userId, String editedUserId) {
        return dao.dListRoleForEdit(userId, editedUserId);
    }

    /**
     * 新增单个用户
     * 
     * @param user
     * @return
     */
    @Override
    public int sInsert(UserForm user) {
        checkRoleLimit(user);
        user.setUserPwd(getDefaultPassword(user.getUserId()));
        user.setInstDate(Tool.DATE.getDate());
        user.setInstTime(Tool.DATE.getTime());
        int[] rs = dao.dInsert(user);
        return rs[0];
    }

    /**
     * 更新单个用户、用户角色关系
     * 
     * @param user
     * @return
     */
    @Override
    public int sUpdate(UserForm user) {
        checkRoleLimit(user);
        user.setModiDate(Tool.DATE.getDate());
        user.setModiTime(Tool.DATE.getTime());
        int[] rs = dao.dUpdate(user);
        return rs[0];
    }

    /**
     * 删除一组用户
     * 
     * @param userIds
     * @return
     */
    @Override
    public int sDelete(String[] userIds) {
        int[] rs = dao.dDelete(userIds);
        return rs[rs.length - 1];
    }

    @Override
    public int sResetPassword(String userId) {
        UserForm user = new UserForm();
        user.setUserId(userId);
        user.setUserPwd(getDefaultPassword(userId));
        user.setModiPwdDate("");
        user.setModiPwdTime("");
        return dao.dUpdatePassword(user);
    }

    @Override
    public int sUpdateUserStatus(String userId, boolean status) {
        UserForm user = new UserForm();
        String userStatus = status ? "1" : "0";
        user.setUserId(userId);
        user.setUserStatus(userStatus);
        return dao.dUpdateUserStatus(user);
    }

    @Override
    public int sUpdateLockFlag(String userId, boolean isLocked) {
        UserForm user = new UserForm();
        user.setUserId(userId);
        String lockFlag = isLocked ? "1" : "0";
        user.setLockFlag(lockFlag);
        if (isLocked) {
            user.setLockDate(Tool.DATE.getDate());
            user.setLockTime(Tool.DATE.getTime());
        } else {
            user.setLockDate("");
            user.setLockTime("");
        }
        return dao.dUpdateLockFlag(user);
    }

    @Override
    public int sOffline(String userId) {
        return dao.dOffline(userId);
    }

    private String getDefaultPassword(String userId) {
        return Tool.STRING.getMd5(userId + "111111");// 需参数化
    }

    private void checkRoleLimit(UserForm user) {
        List<RoleForm> roles = user.getRoles();
        if (null != roles && !roles.isEmpty()) {
            List<Integer> roleIds = new ArrayList<Integer>(roles.size());
            for (RoleForm role : roles) {
                roleIds.add(role.getRoleId());
            }
            RoleLimitBean match = roleLimitService.sFindFirstMatch(roleIds, null);
            if (null != match) {
                Throw.throwRuntimeException(ExceptionCodes.AP020101, match.toString());
            }
        }
    }
}
