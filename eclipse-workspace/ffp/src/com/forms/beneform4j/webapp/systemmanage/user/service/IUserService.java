package com.forms.beneform4j.webapp.systemmanage.user.service;

import java.util.List;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.webapp.systemmanage.role.bean.RoleBean;
import com.forms.beneform4j.webapp.systemmanage.user.bean.UserBean;
import com.forms.beneform4j.webapp.systemmanage.user.form.UserForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 用户管理服务层接口<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-11<br>
 */
public interface IUserService {

    /**
     * 查询用户列表
     * 
     * @param user
     * @param page
     * @return
     */
    List<UserBean> sList(UserForm user, IPage page);

    /**
     * 查找单个用户
     * 
     * @param userId
     * @return
     */
    UserBean sFind(String userId);

    /**
     * 新增用户时查询可维护的角色列表
     * 
     * @param userId
     * @return
     */
    List<RoleBean> sListRoleForAdd(String userId);

    /**
     * 修改用户时查询可维护的角色列表
     * 
     * @param userId
     * @param editedUserId
     * @return
     */
    List<RoleBean> sListRoleForEdit(String userId, String editedUserId);

    /**
     * 新增单个用户
     * 
     * @param user
     * @return
     */
    int sInsert(UserForm user);

    /**
     * 更新单个用户、用户角色关系
     * 
     * @param user
     * @return
     */
    int sUpdate(UserForm user);

    /**
     * 删除一组用户
     * 
     * @param userIds
     * @return
     */
    int sDelete(String[] userIds,String operId);

    /**
     * 重置密码
     * 
     * @param userId
     * @return
     */
    int sResetPassword(String userId);

    /**
     * 更新用户状态
     * 
     * @param userId 用户代码
     * @param status 状态 1 启用 0 停用
     * @return
     */
    int sUpdateUserStatus(String userId,String operId, boolean status);

    /**
     * 更新锁定标志
     * 
     * @param userId 用户代码
     * @param isLocked 是否锁定状态 1 锁定 0 不锁定
     * @return
     */
    int sUpdateLockFlag(String userId, boolean isLocked);

    /**
     * 离线
     * 
     * @param userId 用户代码
     * @return
     */
    int sOffline(String userId);
}
