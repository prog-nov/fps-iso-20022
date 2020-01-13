package com.forms.beneform4j.security.core.login.user.impl;

import com.forms.beneform4j.security.core.access.permission.IPermissionManager;
import com.forms.beneform4j.security.core.access.permission.impl.PermissionManager;
import com.forms.beneform4j.security.core.login.user.IUser;
import com.forms.beneform4j.util.param.single.ISingleParamService;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 用户对象<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-8<br>
 */
public class User implements IUser {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户密码
     */
    private String userPwd;

    /**
     * 用户状态
     */
    private String userStatus;

    /**
     * 机构号
     */
    private String orgId;

    /**
     * 证件类型
     */
    private String certType;

    /**
     * 证件号码
     */
    private String certNo;

    /**
     * 移动电话
     */
    private String mobilePhone;

    /**
     * 电话号码
     */
    private String telephone;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 允许登录的IP
     */
    private String limitIp;

    /**
     * 在线会话数
     */
    private int onlineSessionNum;

    /**
     * 锁定标志
     */
    private String lockFlag;

    /**
     * 锁定日期
     */
    private String lockDate;

    /**
     * 锁定时间
     */
    private String lockTime;

    /**
     * 尝试登录次数
     */
    private int loginNum;

    /**
     * 最后登录IP
     */
    private String lastLoginIp;

    /**
     * 最后登录日期
     */
    private String lastLoginDate;

    /**
     * 最后登录时间
     */
    private String lastLoginTime;

    /**
     * 密码最后修改日期
     */
    private String modiPwdDate;

    /**
     * 密码最后修改时间
     * 
     * @return 密码最后修改时间
     */
    private String modiPwdTime;

    /**
     * 当前角色ID
     */
    private int currentRoleId = -1;

    /**
     * 权限管理器
     */
    private IPermissionManager permissionManager = new PermissionManager();

    /**
     * 配置服务
     */
    private ISingleParamService paramService;

    @Override
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    @Override
    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    @Override
    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    @Override
    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    @Override
    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @Override
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getLimitIp() {
        return limitIp;
    }

    public void setLimitIp(String limitIp) {
        this.limitIp = limitIp;
    }

    @Override
    public int getOnlineSessionNum() {
        return onlineSessionNum;
    }

    public void setOnlineSessionNum(int onlineSessionNum) {
        this.onlineSessionNum = onlineSessionNum;
    }

    @Override
    public String getLockFlag() {
        return lockFlag;
    }

    public void setLockFlag(String lockFlag) {
        this.lockFlag = lockFlag;
    }

    @Override
    public String getLockDate() {
        return lockDate;
    }

    public void setLockDate(String lockDate) {
        this.lockDate = lockDate;
    }

    @Override
    public String getLockTime() {
        return lockTime;
    }

    public void setLockTime(String lockTime) {
        this.lockTime = lockTime;
    }

    @Override
    public int getLoginNum() {
        return loginNum;
    }

    public void setLoginNum(int loginNum) {
        this.loginNum = loginNum;
    }

    @Override
    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    @Override
    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    @Override
    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Override
    public String getModiPwdDate() {
        return modiPwdDate;
    }

    public void setModiPwdDate(String modiPwdDate) {
        this.modiPwdDate = modiPwdDate;
    }

    @Override
    public String getModiPwdTime() {
        return modiPwdTime;
    }

    public void setModiPwdTime(String modiPwdTime) {
        this.modiPwdTime = modiPwdTime;
    }

    @Override
    public int getCurrentRoleId() {
        return currentRoleId;
    }

    @Override
    public void setCurrentRoleId(int currentRoleId) {
        this.currentRoleId = currentRoleId;
    }

    @Override
    public IPermissionManager getPermissionManager() {
        return permissionManager;
    }

    @Override
    public void setPermissionManager(IPermissionManager permissionManager) {
        this.permissionManager = permissionManager;
    }

    @Override
    public ISingleParamService getParamService() {
        return paramService;
    }

    public void setParamService(ISingleParamService paramService) {
        this.paramService = paramService;
    }
}
