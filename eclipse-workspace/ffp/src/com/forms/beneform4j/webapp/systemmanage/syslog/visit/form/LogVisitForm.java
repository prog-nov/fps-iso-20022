package com.forms.beneform4j.webapp.systemmanage.syslog.visit.form;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 访问日志Form <br>
 * Author : OuLinhai <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-7-05 <br>
 */
public class LogVisitForm {

    /**
     * 用户ＩＤ
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 客户端ＩＰ
     */
    private String clientIp;

    /**
     * 服务器ＩＰ
     */
    private String serverIp;

    /**
     * 开始日期
     */
    private String beginDate;

    /**
     * 开始日期
     */
    private String endDate;

    /**
     * 机构号
     */
    private String orgId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

}
