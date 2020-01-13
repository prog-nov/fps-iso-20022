package com.forms.beneform4j.webapp.systemmanage.news.form;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 消息处理formbean<br>
 * Author : luow <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-4<br>
 */
public class NewsForm implements java.io.Serializable {

    private static final long serialVersionUID = 2281243323782050635L;

    /**
     * 消息Id
     */
    private String msgId;

    /**
     * 消息标题
     */
    private String msgTitle;

    /**
     * 消息正文
     */
    private String msgContent;

    /**
     * 目标Url
     */
    private String targetUrl;

    /**
     * 消息类型
     */
    private String msgType;

    /**
     * 消息级别
     */
    private String msgLevel;

    /**
     * 发送人
     */
    private String sendOper;

    /**
     * 发送日期
     */
    private String sendDate;

    /**
     * 发送时间
     */
    private String sendTime;

    /**
     * 接收机构
     */
    private String recvNet;

    /**
     * 接收人
     */
    private String recvOper;

    /**
     * 状态
     */
    private String status;

    /**
     * 消息图标
     */
    private String msgIcon;

    /**
     * 生效标识
     */
    private String effectiveFlag;

    /**
     * 生效天数
     */
    private String effectiveDayCnt;

    /**
     * 生效开始日期
     */
    private String effectiveStartDate;

    /**
     * 生效开始时间
     */
    private String effectiveStartTime;

    /**
     * 生效结束日期
     */
    private String effectiveEndDate;

    /**
     * 生效结束时间
     */
    private String effectiveEndTime;

    /**
     * 消息类型名称
     */
    private String msgTypeName;

    /**
     * 消息级别名称
     */
    private String msgLevelName;

    /**
     * 生效标识名称
     */
    private String effectiveFlagName;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgLevel() {
        return msgLevel;
    }

    public void setMsgLevel(String msgLevel) {
        this.msgLevel = msgLevel;
    }

    public String getSendOper() {
        return sendOper;
    }

    public void setSendOper(String sendOper) {
        this.sendOper = sendOper;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getRecvNet() {
        return recvNet;
    }

    public void setRecvNet(String recvNet) {
        this.recvNet = recvNet;
    }

    public String getRecvOper() {
        return recvOper;
    }

    public void setRecvOper(String recvOper) {
        this.recvOper = recvOper;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsgIcon() {
        return msgIcon;
    }

    public void setMsgIcon(String msgIcon) {
        this.msgIcon = msgIcon;
    }

    public String getEffectiveFlag() {
        return effectiveFlag;
    }

    public void setEffectiveFlag(String effectiveFlag) {
        this.effectiveFlag = effectiveFlag;
    }

    public String getEffectiveDayCnt() {
        return effectiveDayCnt;
    }

    public void setEffectiveDayCnt(String effectiveDayCnt) {
        this.effectiveDayCnt = effectiveDayCnt;
    }

    public String getEffectiveStartDate() {
        return effectiveStartDate;
    }

    public void setEffectiveStartDate(String effectiveStartDate) {
        this.effectiveStartDate = effectiveStartDate;
    }

    public String getEffectiveStartTime() {
        return effectiveStartTime;
    }

    public void setEffectiveStartTime(String effectiveStartTime) {
        this.effectiveStartTime = effectiveStartTime;
    }

    public String getEffectiveEndDate() {
        return effectiveEndDate;
    }

    public void setEffectiveEndDate(String effectiveEndDate) {
        this.effectiveEndDate = effectiveEndDate;
    }

    public String getEffectiveEndTime() {
        return effectiveEndTime;
    }

    public void setEffectiveEndTime(String effectiveEndTime) {
        this.effectiveEndTime = effectiveEndTime;
    }

    public String getMsgTypeName() {
        return msgTypeName;
    }

    public void setMsgTypeName(String msgTypeName) {
        this.msgTypeName = msgTypeName;
    }

    public String getMsgLevelName() {
        return msgLevelName;
    }

    public void setMsgLevelName(String msgLevelName) {
        this.msgLevelName = msgLevelName;
    }

    public String getEffectiveFlagName() {
        return effectiveFlagName;
    }

    public void setEffectiveFlagName(String effectiveFlagName) {
        this.effectiveFlagName = effectiveFlagName;
    }

}
