package com.forms.beneform4j.webapp.systemmanage.guide.form;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : <br>
 * Author : luow <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-28<br>
 */
public class GuideForm {

    /**
     * 菜单Id
     */
    private String menuId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单标识：是否为叶子菜单
     */
    private String menuFlag;

    /**
     * 指引标题
     */
    private String guideTitle;

    /**
     * 指引内容
     */
    private String guideContent;

    /**
     * 初始化日期
     */
    private String instDate;

    /**
     * 初始化时间
     */
    private String instTime;

    /**
     * 初始人
     */
    private String instOper;

    /**
     * 修改日期
     */
    private String modiDate;

    /**
     * 修改时间
     */
    private String modiTime;

    /**
     * 修改人
     */
    private String modiOper;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getGuideTitle() {
        return guideTitle;
    }

    public void setGuideTitle(String guideTitle) {
        this.guideTitle = guideTitle;
    }

    public String getGuideContent() {
        return guideContent;
    }

    public void setGuideContent(String guideContent) {
        this.guideContent = guideContent;
    }

    public String getInstDate() {
        return instDate;
    }

    public void setInstDate(String instDate) {
        this.instDate = instDate;
    }

    public String getInstTime() {
        return instTime;
    }

    public void setInstTime(String instTime) {
        this.instTime = instTime;
    }

    public String getInstOper() {
        return instOper;
    }

    public void setInstOper(String instOper) {
        this.instOper = instOper;
    }

    public String getModiDate() {
        return modiDate;
    }

    public void setModiDate(String modiDate) {
        this.modiDate = modiDate;
    }

    public String getModiTime() {
        return modiTime;
    }

    public void setModiTime(String modiTime) {
        this.modiTime = modiTime;
    }

    public String getModiOper() {
        return modiOper;
    }

    public void setModiOper(String modiOper) {
        this.modiOper = modiOper;
    }

    public String getMenuFlag() {
        return menuFlag;
    }

    public void setMenuFlag(String menuFlag) {
        this.menuFlag = menuFlag;
    }
}
