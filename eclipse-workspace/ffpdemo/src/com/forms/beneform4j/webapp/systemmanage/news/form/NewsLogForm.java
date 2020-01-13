package com.forms.beneform4j.webapp.systemmanage.news.form;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : <br>
 * Author : luow <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-28<br>
 */
public class NewsLogForm implements java.io.Serializable {

    private static final long serialVersionUID = -7816377068967028548L;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 消息Id
     */
    private String msgId;

    /**
     * 操作日期
     */
    private String operDate;

    /**
     * 操作时间
     */
    private String operTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getOperDate() {
        return operDate;
    }

    public void setOperDate(String operDate) {
        this.operDate = operDate;
    }

    public String getOperTime() {
        return operTime;
    }

    public void setOperTime(String operTime) {
        this.operTime = operTime;
    }

}
