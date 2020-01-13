package com.forms.beneform4j.webapp.portal.memo.form;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 备忘录实体对象<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-27<br>
 */
public class MemoForm {

    /**
     * 唯一索引
     */
    private String id;

    /**
     * 日志Id
     */
    private String logId;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 标题
     */
    private String memoTitle;

    /**
     * 正文
     */
    private String memoContent;

    /**
     * 备忘日期
     */
    private String memoDate;

    /**
     * 备忘时间
     */
    private String memoTime;

    /**
     * // 0 : 不需要提醒 1： 需要提醒
     */
    private int isRemind;

    /**
     * // 0 : 未完成 1： 已完成
     */
    private int status;

    /**
     * 操作日期
     */
    private String opDate;

    /**
     * 操作时间
     */
    private String opTime;

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public int getIsRemind() {
        return isRemind;
    }

    public void setIsRemind(int isRemind) {
        this.isRemind = isRemind;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMemoTitle() {
        return memoTitle;
    }

    public void setMemoTitle(String memoTitle) {
        this.memoTitle = memoTitle;
    }

    public String getMemoContent() {
        return memoContent;
    }

    public void setMemoContent(String memoContent) {
        this.memoContent = memoContent;
    }

    public String getMemoDate() {
        return memoDate;
    }

    public void setMemoDate(String memoDate) {
        this.memoDate = memoDate;
    }

    public String getMemoTime() {
        return memoTime;
    }

    public void setMemoTime(String memoTime) {
        this.memoTime = memoTime;
    }

    public String getOpDate() {
        return opDate;
    }

    public void setOpDate(String opDate) {
        this.opDate = opDate;
    }

    public String getOpTime() {
        return opTime;
    }

    public void setOpTime(String opTime) {
        this.opTime = opTime;
    }

}
