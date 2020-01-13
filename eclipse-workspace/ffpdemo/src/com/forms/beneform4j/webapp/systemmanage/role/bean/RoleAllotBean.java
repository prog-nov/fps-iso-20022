package com.forms.beneform4j.webapp.systemmanage.role.bean;

import com.forms.beneform4j.webapp.common.bean.LogableBean;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 角色（分配）信息Bean对象<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-14<br>
 */
public class RoleAllotBean extends LogableBean {

    private static final long serialVersionUID = 2596355733354258941L;

    /**
     * 角色（分配）ID
     */
    private int roleAllotId;

    /**
     * 角色（分配）名称
     */
    private String roleAllotName;

    /**
     * 描述
     */
    private String des;

    /**
     * 是否勾选，用于维护角色-角色（分配）关系
     */
    private int checked;

    public int getRoleAllotId() {
        return roleAllotId;
    }

    public void setRoleAllotId(int roleAllotId) {
        this.roleAllotId = roleAllotId;
    }

    public String getRoleAllotName() {
        return roleAllotName;
    }

    public void setRoleAllotName(String roleAllotName) {
        this.roleAllotName = roleAllotName;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }
}
