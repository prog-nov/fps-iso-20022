package com.forms.beneform4j.security.core.login.user;

import com.forms.beneform4j.security.core.access.permission.IPermissionManager;
import com.forms.beneform4j.util.param.single.ISingleParamService;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 用户对象接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-8<br>
 */
public interface IUser {

    /**
     * 表示用户默认角色的选型名称
     */
    public String DEFAULT_ROLE_OPTION_NAME = "USER_DEFAULT_ROLE";

    /**
     * 获取用户ID
     * 
     * @return 用户ID
     */
    public String getUserId();

    /**
     * 获取用户名称
     * 
     * @return 用户名称
     */
    public String getUserName();

    /**
     * 获取用户昵称
     * 
     * @return 用户昵称
     */
    public String getNickName();

    /**
     * 获取用户密码
     * 
     * @return 用户密码
     */
    public String getUserPwd();

    /**
     * 获取用户状态
     * 
     * @return 用户状态
     */
    public String getUserStatus();

    /**
     * 获取机构号
     * 
     * @return 机构号
     */
    public String getOrgId();

    /**
     * 获取证件类型
     * 
     * @return 证件类型
     */
    public String getCertType();

    /**
     * 获取证件号码
     * 
     * @return 证件号码
     */
    public String getCertNo();

    /**
     * 获取移动电话
     * 
     * @return 移动电话号码
     */
    public String getMobilePhone();

    /**
     * 获取电话号码
     * 
     * @return 电话号码
     */
    public String getTelephone();

    /**
     * 获取电子邮箱
     * 
     * @return 电子邮箱
     */
    public String getEmail();

    /**
     * 获取允许登录的IP
     * 
     * @return 允许登录的IP，多个IP使用逗号分隔
     */
    public String getLimitIp();

    /**
     * 获取在线会话数
     * 
     * @return 在线会话数
     */
    public int getOnlineSessionNum();

    /**
     * 获取锁定标志
     * 
     * @return 锁定标志
     */
    public String getLockFlag();

    /**
     * 获取锁定日期
     * 
     * @return 锁定日期
     */
    public String getLockDate();

    /**
     * 获取锁定时间
     * 
     * @return 锁定时间
     */
    public String getLockTime();

    /**
     * 获取尝试登录次数
     * 
     * @return 尝试登录次数
     */
    public int getLoginNum();

    /**
     * 获取最后登录IP
     * 
     * @return 最后登录IP
     */
    public String getLastLoginIp();

    /**
     * 获取最后登录日期
     * 
     * @return 最后登录日期
     */
    public String getLastLoginDate();

    /**
     * 获取最后登录时间
     * 
     * @return 最后登录时间
     */
    public String getLastLoginTime();

    /**
     * 获取密码最后修改日期
     * 
     * @return 密码最后修改日期
     */
    public String getModiPwdDate();

    /**
     * 获取密码最后修改时间
     * 
     * @return 密码最后修改时间
     */
    public String getModiPwdTime();

    /**
     * 获取当前角色ID
     * 
     * @return 当前角色ID，如果是多角色混杂模式，设置为-1
     */
    public int getCurrentRoleId();

    /**
     * 设置当前角色ID
     * 
     * @param currentRoleId
     */
    public void setCurrentRoleId(int currentRoleId);

    /**
     * 获取权限管理器
     * 
     * @return
     */
    public IPermissionManager getPermissionManager();

    /**
     * 设置权限管理器
     * 
     * @param permissionManager
     */
    public void setPermissionManager(IPermissionManager permissionManager);

    /**
     * 获取配置服务
     * 
     * @return 配置服务
     */
    public ISingleParamService getParamService();

    /**
     * 设置配置服务
     * 
     * @param paramService 配置服务
     */
    public void setParamService(ISingleParamService paramService);
}
