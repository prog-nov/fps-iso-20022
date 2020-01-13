package com.forms.beneform4j.webapp.systemmanage.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.forms.beneform4j.core.dao.annotation.BatchParam;
import com.forms.beneform4j.core.dao.annotation.Execute;
import com.forms.beneform4j.core.dao.annotation.Executes;
import com.forms.beneform4j.core.dao.annotation.SqlRef;
import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.webapp.systemmanage.role.bean.RoleBean;
import com.forms.beneform4j.webapp.systemmanage.user.bean.UserBean;
import com.forms.beneform4j.webapp.systemmanage.user.form.UserForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 用户数据访问层<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-11<br>
 */
@Repository("userDao")
public interface IUserDao {

    /**
     * 查询用户列表
     * 
     * @param user
     * @param page
     * @return
     */
    public List<UserBean> dList(UserForm user, IPage page);

    /**
     * 查找单个用户
     * 
     * @param userId
     * @return
     */
    @SqlRef("dList")
    public UserBean dFind(@Param("userId") String userId);

    /**
     * 新增用户时查询可维护的角色列表
     * 
     * @param userId
     * @return
     */
    public List<RoleBean> dListRoleForAdd(@Param("userId") String userId);

    /**
     * 修改用户时查询可维护的角色列表
     * 
     * @param userId
     * @param editedUserId
     * @return
     */
    public List<RoleBean> dListRoleForEdit(@Param("userId") String userId, @Param("editedUserId") String editedUserId);

    /**
     * 新增单个用户
     * 
     * @param user
     * @return
     */
    @Executes({
            // 新增用户信息
            @Execute(sqlRef = @SqlRef("dInsertUser"), param = @BatchParam(false)),
            // 新增用户角色关系
            @Execute(sqlRef = @SqlRef("dInsertUserRole"), param = @BatchParam(item = "role", property = "roles"))})
    public int[] dInsert(UserForm user);

    /**
     * 更新单个用户、用户角色关系
     * 
     * @param user
     * @return
     */
    @Executes({
            // 更新用户信息
            @Execute(sqlRef = @SqlRef("dUpdateUser"), param = @BatchParam(false)),
            // 删除用户角色关系
            @Execute(sqlRef = @SqlRef("dDeleteUserRole"), param = @BatchParam(value = false, property = "userId")),
            // 重新添加用户角色关系
            @Execute(sqlRef = @SqlRef("dInsertUserRole"), param = @BatchParam(item = "role", property = "roles"))})
    public int[] dUpdate(UserForm user);

    /**
     * 删除一组用户
     * 
     * @param userIds
     * @return
     */
    @Executes({
            // 删除用户配置
            @Execute(sqlRef = @SqlRef("dDeleteUserCfg"), param = @BatchParam(item = "userId")),
            // 删除用户与用户关系
            @Execute(sqlRef = @SqlRef("dDeleteUserRole"), param = @BatchParam(item = "userId")),
            // 删除用户信息
            @Execute(sqlRef = @SqlRef("dDeleteUser"), param = @BatchParam(item = "userId"))})
    public int[] dDelete(String[] userIds);

    /**
     * 更新用户密码
     * 
     * @param user
     * @return
     */
    public int dUpdatePassword(UserForm user);

    /**
     * 更新用户状态
     * 
     * @param user
     * @return
     */
    public int dUpdateUserStatus(UserForm user);

    /**
     * 更新锁定标志
     * 
     * @param user
     * @return
     */
    public int dUpdateLockFlag(UserForm user);

    /**
     * 强制离线
     * 
     * @param userId
     * @return
     */
    public int dOffline(@Param("userId") String userId);

}
