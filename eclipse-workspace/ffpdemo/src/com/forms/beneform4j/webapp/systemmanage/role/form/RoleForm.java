package com.forms.beneform4j.webapp.systemmanage.role.form;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.forms.beneform4j.webapp.common.form.LogableForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 角色维护请求表单<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-3<br>
 */
public class RoleForm extends LogableForm {

    /**
     * 
     */
    private static final long serialVersionUID = -1054786094870416052L;

    /**
     * 角色ID
     */
    private int roleId;

    /**
     * 角色名称
     */
    @NotBlank
    private String roleName;

    /**
     * 角色描述
     */
    private String des;

    /**
     * 角色权限
     */
    private List<PermTypeForm> permTypes;

    /**
     * 角色（分配）是否加载的标志
     */
    private boolean roleAllotLoaded;

    /**
     * 角色（分配）
     */
    private List<RoleAllotForm> roleAllots;

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

    public boolean isRoleAllotLoaded() {
        return roleAllotLoaded;
    }

    public void setRoleAllotLoaded(boolean roleAllotLoaded) {
        this.roleAllotLoaded = roleAllotLoaded;
    }

    public List<RoleAllotForm> getRoleAllots() {
        return roleAllots;
    }

    public void setRoleAllots(List<RoleAllotForm> roleAllots) {
        this.roleAllots = roleAllots;
    }
}
