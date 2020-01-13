package com.forms.beneform4j.security.core.access.permission.impl;

import com.forms.beneform4j.security.core.access.permission.IMenuPermission;
import com.forms.beneform4j.util.tree.impl.TreeNode;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 菜单权限实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-19<br>
 */
public class MenuPermission extends TreeNode implements IMenuPermission {

    private static final long serialVersionUID = -1425109593985494384L;

    /**
     * 权限ID
     */
    private int permId;

    /**
     * 附加属性
     */
    private String permAttr;

    /**
     * 子菜单显示区域
     */
    private String displayArea;

    /**
     * 依赖菜单
     */
    private int dependMenuId = -1;

    /**
     * 授权级别
     */
    private String authorLevel;

    /**
     * 菜单项或功能项
     */
    private String menuFlag;

    /**
     * 是否在权限树显示
     */
    private String permTreeFlag;

    /**
     * 目标页面
     */
    private String targetPage;

    @Override
    public int getPermId() {
        return permId;
    }

    public void setPermId(int permId) {
        this.permId = permId;
    }

    @Override
    public String getPermAttr() {
        return permAttr;
    }

    public void setPermAttr(String permAttr) {
        this.permAttr = permAttr;
    }

    @Override
    public String getDisplayArea() {
        return displayArea;
    }

    public void setDisplayArea(String displayArea) {
        this.displayArea = displayArea;
    }

    @Override
    public int getDependMenuId() {
        return dependMenuId;
    }

    public void setDependMenuId(int dependMenuId) {
        this.dependMenuId = dependMenuId;
    }

    @Override
    public String getAuthorLevel() {
        return authorLevel;
    }

    public void setAuthorLevel(String authorLevel) {
        this.authorLevel = authorLevel;
    }

    @Override
    public String getMenuFlag() {
        return menuFlag;
    }

    public void setMenuFlag(String menuFlag) {
        this.menuFlag = menuFlag;
    }

    @Override
    public String getPermTreeFlag() {
        return permTreeFlag;
    }

    public void setPermTreeFlag(String permTreeFlag) {
        this.permTreeFlag = permTreeFlag;
    }

    @Override
    public String getTargetPage() {
        return targetPage;
    }

    public void setTargetPage(String targetPage) {
        this.targetPage = targetPage;
    }
}
