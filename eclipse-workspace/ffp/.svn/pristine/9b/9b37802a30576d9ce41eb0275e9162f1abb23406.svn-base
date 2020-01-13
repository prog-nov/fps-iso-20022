package com.forms.beneform4j.webapp.systemmanage.org.form;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.forms.beneform4j.webapp.common.form.LogableForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 机构维护 <br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-9 <br>
 */
public class OrgForm extends LogableForm {

    private static final long serialVersionUID = -2845885387626816604L;

    public interface Insert {
    };// 机构号，机构名称，父机构不能为空
    public interface Update {
    };// 机构号，机构名称不能为空，（父机构只能通过移动来改变，所以这里可以为空）
    public interface Move {
    };// 机构号，父机构不能为空

    /**
     * BF_ORG.ORG_ID 机构号
     */
    @Size(max = 20, min = 2, groups = {Insert.class, Update.class, Move.class}, message = "{Size.orgForm.orgId}")
    private String orgId;

    /**
     * BF_ORG.ORG_NAME 机构名称
     */
    @NotBlank(groups = {Insert.class, Update.class}, message = "{NotBlank.orgForm.orgName}")
    @Size(min = 2, groups = {Insert.class, Update.class}, message = "{Size.orgForm.orgName}")
    private String orgName;

    /**
     * BF_ORG.SUP_ORG_ID
     */
    @Pattern(regexp = "\\w{2,10}", groups = {Insert.class, Move.class}, message = "{Size.orgForm.supOrgId}")
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
     * 最小机构的级别
     */
    private int minOrgLevel = -1;

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

    public int getMinOrgLevel() {
        return minOrgLevel;
    }

    public void setMinOrgLevel(int minOrgLevel) {
        this.minOrgLevel = minOrgLevel;
    }
}
