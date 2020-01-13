package com.forms.beneform4j.webapp.common.favorites.form;

import com.forms.beneform4j.webapp.common.form.LogableForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 快捷菜单 <br>
 * Author : zhangjj <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-8-22 <br>
 */
public class FavoritesForm extends LogableForm {

    private static final long serialVersionUID = -5958935384560866501L;

    /**
     * 代理主键
     */
    private String keyId;
    /**
     * 父ID，和代理主键对应
     */
    private String parentId;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 菜单ID
     */
    private String menuId;
    /**
     * 快捷菜单名称
     */
    private String shortMenuName;
    /**
     * 显示图标
     */
    private String displayIcon;
    /**
     * 排序
     */
    private String seqno;
    /**
     * 描述
     */
    private String des;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 菜单数显示类型：0－全部，1－已收藏，2－未收藏
     */
    private String displayType;

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getShortMenuName() {
        return shortMenuName;
    }

    public void setShortMenuName(String shortMenuName) {
        this.shortMenuName = shortMenuName;
    }

    public String getDisplayIcon() {
        return displayIcon;
    }

    public void setDisplayIcon(String displayIcon) {
        this.displayIcon = displayIcon;
    }

    public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

}
