package com.forms.beneform4j.webapp.systemmanage.menulocale.bean;

import java.io.Serializable;
import java.util.List;

import com.forms.beneform4j.webapp.common.bean.LogableBean;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : Beneform4j开发平台 ̨ <br>
 * Description : BF_MENU <br>
 * Author : XGP <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-10-31 <br>
 */
public class BfMenuBean extends LogableBean implements Serializable {

    private static final long serialVersionUID = -2796294300758478553L;

    /**
     * 菜单Id
     */
    private int menuId;

    /**
     * 父菜单Id
     */
    private int parentMenuId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单URL
     */
	private String menuUrl;

	/**
     * 显示区域
     */
    private String displayArea;

    /**
     * 显示图标
     */
    private String displayIcon;

    /**
     * 依赖菜单
     */
    private int dependMenuId;

    /**
     * 授权级别
     */
    private String authorLevel;

    /**
     * 菜单标志
     */
    private String menuFlag;

    /**
     * 权限树标志
     */
    private String permTreeFlag;

    /**
     * 目标页
     */
    private String targetPage;

    /**
     * 排序
     */
    private int seqno;

    /**
     * 描述
     */
    private String des;

    /**
     * 数量
     */
    private int count;

    /**
     * 本地菜单
     */
    private List<BfMenuLocaleBean> localeMenus;

    public List<BfMenuLocaleBean> getLocaleMenus() {
        return localeMenus;
    }

    public void setLocaleMenus(List<BfMenuLocaleBean> localeMenus) {
        this.localeMenus = localeMenus;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(int parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getDisplayArea() {
        return displayArea;
    }

    public void setDisplayArea(String displayArea) {
        this.displayArea = displayArea;
    }

    public String getDisplayIcon() {
        return displayIcon;
    }

    public void setDisplayIcon(String displayIcon) {
        this.displayIcon = displayIcon;
    }

    public int getDependMenuId() {
        return dependMenuId;
    }

    public void setDependMenuId(int dependMenuId) {
        this.dependMenuId = dependMenuId;
    }

    public String getAuthorLevel() {
        return authorLevel;
    }

    public void setAuthorLevel(String authorLevel) {
        this.authorLevel = authorLevel;
    }

    public String getMenuFlag() {
        return menuFlag;
    }

    public void setMenuFlag(String menuFlag) {
        this.menuFlag = menuFlag;
    }

    public String getPermTreeFlag() {
        return permTreeFlag;
    }

    public void setPermTreeFlag(String permTreeFlag) {
        this.permTreeFlag = permTreeFlag;
    }

    public String getTargetPage() {
        return targetPage;
    }

    public void setTargetPage(String targetPage) {
        this.targetPage = targetPage;
    }

    public int getSeqno() {
        return seqno;
    }

    public void setSeqno(int seqno) {
        this.seqno = seqno;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
