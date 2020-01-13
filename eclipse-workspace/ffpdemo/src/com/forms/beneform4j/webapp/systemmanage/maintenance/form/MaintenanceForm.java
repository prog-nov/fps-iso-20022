package com.forms.beneform4j.webapp.systemmanage.maintenance.form;

import java.util.List;

import com.forms.beneform4j.webapp.common.form.LogableForm;
import com.forms.beneform4j.webapp.systemmanage.role.form.RoleForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 角色-角色(分配)关系维护<br>
 * Author : WangXiaoYing<br>
 * Version : 1.1.0 <br>
 * Since : 1.1.0 <br>
 * Date : 2017-3-13<br>
 */
public class MaintenanceForm extends LogableForm {
    private static final long serialVersionUID = -2796294300758478553L;

    /**
     * ROLE_ID 角色ID
     */
    private int roleId;

    /**
     * ROLE_ALLOT_ID 角色(分配)ID
     */
    private int roleAllotId;

    /**
     * ROLE_NAME 角色名称
     */
    private String roleName;

    /**
     * ROLE_ALLOT_NAME 角色(分配)名称
     */
    private String roleAllotName;

    private List<RoleForm> roles;

    public List<RoleForm> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleForm> roles) {
        this.roles = roles;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleAllotId() {
        return roleAllotId;
    }

    public void setRoleAllotId(int roleAllotId) {
        this.roleAllotId = roleAllotId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleAllotName() {
        return roleAllotName;
    }

    public void setRoleAllotName(String roleAllotName) {
        this.roleAllotName = roleAllotName;
    }
}
