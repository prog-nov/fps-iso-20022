package com.forms.beneform4j.webapp.systemmanage.task.taskcollect.bean;

import java.util.List;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 任务采集基本bean<br>
 * Author : lsc <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-9-20<br>
 */
public class TaskComBean {

    /**
     * 任务主键号 可以是业务主键也可以是工作流的activid 系统自动生成
     */
    private String keyId;

    /**
     * 业务主键 业务信息要素
     */
    private String busiKey;

    /**
     * 当前任务编号
     */
    private String currentTaskId;

    /**
     * 下一个任务编号
     */
    private String nextTaskId;

    /**
     * 跳转链接
     */
    private String targetUrl;

    /**
     * 任务日期
     */
    private String taskDate;

    /**
     * 任务时间
     */
    private String taskTime;

    /**
     * 任务参数(json格式)
     */
    private String params;

    /**
     * 限定用户集合 userId
     */
    private List<String> usersList;

    /**
     * 限定角色集合 map 对应的 key=roleId
     */
    private List<String> rolesList;

    /**
     * 限定机构集合 map 对应的 key=orgId
     */
    private List<String> orgsList;

    /**
     * 当前处理人
     */
    private String dealOper;

    private String dealDate;

    private String dealTime;

    private String dealOrgId;

    /**
     * 日志记录ID 系统自动生成
     */
    private String logId;

    public String getDealOrgId() {
        return dealOrgId;
    }

    public void setDealOrgId(String dealOrgId) {
        this.dealOrgId = dealOrgId;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getBusiKey() {
        return busiKey;
    }

    public void setBusiKey(String busiKey) {
        this.busiKey = busiKey;
    }

    public String getCurrentTaskId() {
        return currentTaskId;
    }

    public void setCurrentTaskId(String currentTaskId) {
        this.currentTaskId = currentTaskId;
    }

    public String getNextTaskId() {
        return nextTaskId;
    }

    public void setNextTaskId(String nextTaskId) {
        this.nextTaskId = nextTaskId;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    public String getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public List<String> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<String> usersList) {
        this.usersList = usersList;
    }

    public List<String> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<String> rolesList) {
        this.rolesList = rolesList;
    }

    public List<String> getOrgsList() {
        return orgsList;
    }

    public void setOrgsList(List<String> orgsList) {
        this.orgsList = orgsList;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getDealOper() {
        return dealOper;
    }

    public void setDealOper(String dealOper) {
        this.dealOper = dealOper;
    }

    public String getDealDate() {
        return dealDate;
    }

    public void setDealDate(String dealDate) {
        this.dealDate = dealDate;
    }

    public String getDealTime() {
        return dealTime;
    }

    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }
}
