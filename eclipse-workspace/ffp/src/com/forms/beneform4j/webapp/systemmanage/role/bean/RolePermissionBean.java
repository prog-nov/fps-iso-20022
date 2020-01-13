package com.forms.beneform4j.webapp.systemmanage.role.bean;

import java.io.Serializable;

import com.forms.beneform4j.util.tree.impl.TreeNode;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 角色的权限信息Bean实体，由于需要继承树型节点，这里赞不移入domain项目<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-3<br>
 */
public class RolePermissionBean extends TreeNode implements Serializable {

    private static final long serialVersionUID = 2702911214900603658L;

    /**
     * 权限ID
     */
    private int permId;

    /**
     * 权限属性
     */
    private String permAttr;

    public int getPermId() {
        return permId;
    }

    public void setPermId(int permId) {
        this.permId = permId;
    }

    public String getPermAttr() {
        return permAttr;
    }

    public void setPermAttr(String permAttr) {
        this.permAttr = permAttr;
    }
}
