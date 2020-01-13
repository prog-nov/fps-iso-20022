package com.forms.beneform4j.webapp.systemmanage.role.form;

import java.util.ArrayList;
import java.util.List;

import com.forms.beneform4j.webapp.common.form.LogableForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 角色（分配）维护请求表单<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-14<br>
 */
public class RoleAllotForm extends LogableForm {

    /**
     * 
     */
    private static final long serialVersionUID = -1054786094870416052L;

    /**
     * 角色（分配）ID
     */
    private int roleAllotId;

    /**
     * 角色（分配）名称
     */
    private String roleAllotName;

    /**
     * 角色（分配）描述
     */
    private String des;

    /**
     * 角色（分配）权限
     */
    private List<PermTypeForm> permTypes;

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

    public List<PermTypeForm> getPermTypes() {
        return permTypes;
    }

    public void setPermTypes(List<PermTypeForm> permTypes) {
        this.permTypes = permTypes;
    }

    public List<RolePermissionForm> getPermissions() {
        List<PermTypeForm> permTypes = getPermTypes();
        if (null != permTypes && !permTypes.isEmpty()) {
            List<RolePermissionForm> permissions = new ArrayList<RolePermissionForm>();
            for (PermTypeForm permType : permTypes) {
                if (null != permType.getPermissions()) {
                    permissions.addAll(permType.getPermissions());
                }
            }
            return permissions;
        }
        return null;
    }
}
