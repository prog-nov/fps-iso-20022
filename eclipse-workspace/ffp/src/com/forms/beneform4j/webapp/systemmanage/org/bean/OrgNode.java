package com.forms.beneform4j.webapp.systemmanage.org.bean;

import com.forms.beneform4j.util.tree.impl.TreeNode;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 机构节点，同步加载使用 <br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-12 <br>
 */
public class OrgNode extends TreeNode {

    private static final long serialVersionUID = -2845885387626816604L;

    /**
     * BF_ORG.ORG_LEVEL 机构级别
     */
    private int orgLevel;

    /**
     * BF_ORG.ORG_TYPE 机构类型（预留）
     */
    private String orgType;

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
}
