package com.forms.beneform4j.webapp.systemmanage.role.form;

import java.util.List;

import com.forms.beneform4j.webapp.common.form.LogableForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 角色约束信息表单对象<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-4<br>
 */
public class RoleLimitForm extends LogableForm {

    /**
     * 
     */
    private static final long serialVersionUID = 2596355733354258941L;

    /**
     * 约束编号
     */
    private String limitNo;

    /**
     * 约束描述
     */
    private String des;

    /**
     * 角色ID组
     */
    private List<Integer> roleIds;

    /**
     * 角色ID
     */
    private int roleId;

    /**
     * 角色名称
     */
    private String roleName;

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

    public List<Integer> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Integer> roleIds) {
        this.roleIds = roleIds;
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
}
