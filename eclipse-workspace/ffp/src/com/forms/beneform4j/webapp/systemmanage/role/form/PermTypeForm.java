package com.forms.beneform4j.webapp.systemmanage.role.form;

import java.io.Serializable;
import java.util.List;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 权限类型<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-15<br>
 */
public class PermTypeForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3781211282928299725L;

    /**
     * 权限类型
     */
    private String permType;

    /**
     * 角色权限
     */
    private List<RolePermissionForm> permissions;

    public String getPermType() {
        return permType;
    }

    public void setPermType(String permType) {
        this.permType = permType;
    }

    public List<RolePermissionForm> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<RolePermissionForm> permissions) {
        this.permissions = permissions;
    }
}
