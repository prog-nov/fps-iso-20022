package com.forms.beneform4j.webapp.systemmanage.task.taskrule.bean;

import java.io.Serializable;

import com.forms.beneform4j.util.tree.impl.TreeNode;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 任务规则机构列表bean<br>
 * Author : lsc <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-8-23<br>
 */
public class TaskOrgBean extends TreeNode implements Serializable {

    private static final long serialVersionUID = -2861909068251122085L;

    /**
     * 机构号
     */
    private String orgId;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

}
