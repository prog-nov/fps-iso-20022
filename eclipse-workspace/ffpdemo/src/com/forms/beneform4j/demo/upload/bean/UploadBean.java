package com.forms.beneform4j.demo.upload.bean;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 上传BEAN<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-28<br>
 */
public class UploadBean {

    private String[] fileKeys;
    private String[] fileKeysAuto;
    private String title;
    private String content;

    public String[] getFileKeys() {
        return fileKeys;
    }

    public String[] getFileKeysAuto() {
        return fileKeysAuto;
    }

    public void setFileKeysAuto(String[] fileKeysAuto) {
        this.fileKeysAuto = fileKeysAuto;
    }

    public void setFileKeys(String[] fileKeys) {
        this.fileKeys = fileKeys;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
