package com.forms.beneform4j.webapp.common.docmanager.bean;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 附件上传下载的服务bean<br>
 * Author : luow <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-16<br>
 */
public class DocBean extends AttachBean {

    /**
     * 文档Id
     */
    private String docId;

    /**
     * 文档名称
     */
    private String docName;

    /**
     * 前缀
     */
    private String suffix;

    /**
     * 校验类型
     */
    private String checkSumType;

    /**
     * 校验值
     */
    private String checkSum;

    /**
     * 文档大小
     */
    private long docSize;

    /**
     * 文档类型
     */
    private String docType;

    /**
     * 文档状态
     */
    private String docState;

    /**
     * 保存路径
     */
    private String storePath;

    /**
     * 初始化日期
     */
    private String instDate;

    /**
     * 初始化时间
     */
    private String instTime;

    /**
     * 操作人
     */
    private String instOper;

    /**
     * 更新日期
     */
    private String modiDate;

    /**
     * 更新时间
     */
    private String modiTime;

    /**
     * 更新人
     */
    private String modiOper;

    /**
     * 文档状态显示名称
     */
    private String docStateName;

    /**
     * 文档类型显示名称
     */
    private String docTypeName;

    /**
     * 临时保存路径
     */
    private String storePathTemp;

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getCheckSumType() {
        return checkSumType;
    }

    public void setCheckSumType(String checkSumType) {
        this.checkSumType = checkSumType;
    }

    public String getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(String checkSum) {
        this.checkSum = checkSum;
    }

    public long getDocSize() {
        return docSize;
    }

    public void setDocSize(long docSize) {
        this.docSize = docSize;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocState() {
        return docState;
    }

    public void setDocState(String docState) {
        this.docState = docState;
    }

    public String getStorePath() {
        return storePath;
    }

    public void setStorePath(String storePath) {
        this.storePath = storePath;
    }

    public String getInstDate() {
        return instDate;
    }

    public void setInstDate(String instDate) {
        this.instDate = instDate;
    }

    public String getInstTime() {
        return instTime;
    }

    public void setInstTime(String instTime) {
        this.instTime = instTime;
    }

    public String getInstOper() {
        return instOper;
    }

    public void setInstOper(String instOper) {
        this.instOper = instOper;
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

    public String getDocStateName() {
        return docStateName;
    }

    public void setDocStateName(String docStateName) {
        this.docStateName = docStateName;
    }

    public String getDocTypeName() {
        return docTypeName;
    }

    public void setDocTypeName(String docTypeName) {
        this.docTypeName = docTypeName;
    }

    public String getStorePathTemp() {
        return storePathTemp;
    }

    public void setStorePathTemp(String storePathTemp) {
        this.storePathTemp = storePathTemp;
    }

}
