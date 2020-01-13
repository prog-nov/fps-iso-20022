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
 * Date : 2016-12-28 <br>
 */

public class BfGuideBean extends LogableBean implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -2796294300758478553L;

    /**
     * BF_MENU.MENU_ID ID
     */
    private int menuId;

    private int parentMenuId;

    /**
     * BF_MENU.MENU_NAME 菜单名称
     */
    private String menuName;

    /**
     * BF_MENU.MENU_URL 菜单URL
     */
    
    /**
     * 加载操作指引国际化数据
    */
    private List<BfMenuGuideBean> guideMenus;
    

	public List<BfMenuGuideBean> getGuideMenus() {
		return guideMenus;
	}

	public void setGuideMenus(List<BfMenuGuideBean> guideMenus) {
		this.guideMenus = guideMenus;
	}

	private String menuUrl;

    private String displayArea;

    private String displayIcon;

    private int dependMenuId;

    private String authorLevel;

    private String menuFlag;

    private String permTreeFlag;

    private String targetPage;

    private int seqno;

    private String des;

    private int count;

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
