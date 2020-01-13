package com.forms.beneform4j.webapp.systemmanage.user.bean;

import com.forms.beneform4j.security.core.login.user.impl.User;
import com.forms.beneform4j.util.tree.ITree;
import com.forms.beneform4j.util.tree.ITreeNode;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 用户实体对象<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-6<br>
 */
public class UserInfo extends User {

    /**
     * 剩余尝试登录次数
     */
    private int leftTryLoginNum;

    /**
     * 会话Id
     */
    private String sessionId;

    /**
     * 新密码
     */
    private String newPassword;

    /**
     * 机构名称
     */
    private String orgName;

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

    /**
     * 菜单列表
     */
    ITree<? extends ITreeNode> tree;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public ITree<? extends ITreeNode> getTree() {
        return tree;
    }

    public void setTree(ITree<? extends ITreeNode> tree) {
        this.tree = tree;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public int getLeftTryLoginNum() {
        return leftTryLoginNum;
    }

    public void setLeftTryLoginNum(int leftTryLoginNum) {
        this.leftTryLoginNum = leftTryLoginNum;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
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

}
