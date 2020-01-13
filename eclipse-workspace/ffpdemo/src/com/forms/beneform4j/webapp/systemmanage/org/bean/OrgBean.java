package com.forms.beneform4j.webapp.systemmanage.org.bean;

import com.forms.beneform4j.webapp.common.bean.LogableBean;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 机构维护 <br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-6 <br>
 */
public class OrgBean extends LogableBean {

    private static final long serialVersionUID = -2845885387626816604L;

    /**
     * BF_ORG.ORG_ID 机构号
     */
    private String orgId;

    /**
     * BF_ORG.ORG_NAME 机构名称
     */
    private String orgName;

    /**
     * BF_ORG.SUP_ORG_ID
     */
    private String supOrgId;

    /**
     * BF_ORG.ORG_LEVEL 机构级别
     */
    private int orgLevel;

    /**
     * BF_ORG.ORG_TYPE 机构类型（预留）
     */
    private String orgType;

    /**
     * BF_ORG.DES 描述
     */
    private String des;

    /**
     * 直接子机构数量
     */
    private int count;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getSupOrgId() {
        return supOrgId;
    }

    public void setSupOrgId(String supOrgId) {
        this.supOrgId = supOrgId;
    }

    public int getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(int orgLevel) {
        this.orgLevel = orgLevel;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
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
