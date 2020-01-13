package com.forms.beneform4j.webapp.systemmanage.role.bean;

import java.util.List;

import com.forms.beneform4j.webapp.common.bean.LogableBean;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 角色约束信息Bean对象<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-4<br>
 */
public class RoleLimitBean extends LogableBean {

    private static final long serialVersionUID = 2596355733354258941L;

    /**
     * 约束编号
     */
    private String limitNo;

    /**
     * 描述
     */
    private String des;

    /**
     * 角色数
     */
    private int roleNum;

    /**
     * 角色ID
     */
    private int roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String roleDes;

    /**
     * 角色
     */
    private List<RoleBean> roles;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (RoleBean role : getRoles()) {
            sb.append("、").append(role.getRoleName());
        }
        String des = "[" + getLimitNo() + "]";
        if (null != getDes()) {
            des += "[" + getDes() + "]";
        }
        des = des + "[" + sb.substring(1) + "]";
        return des;
    }

    public String getLimitNo() {
        return limitNo;
    }

    public void setLimitNo(String limitNo) {
        this.limitNo = limitNo;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getRoleNum() {
        return roleNum;
    }

    public void setRoleNum(int roleNum) {
        this.roleNum = roleNum;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDes() {
        return roleDes;
    }

    public void setRoleDes(String roleDes) {
        this.roleDes = roleDes;
    }

    public List<RoleBean> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleBean> roles) {
        this.roles = roles;
    }
}
