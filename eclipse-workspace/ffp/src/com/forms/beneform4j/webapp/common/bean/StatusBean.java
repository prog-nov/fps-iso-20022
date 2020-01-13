package com.forms.beneform4j.webapp.common.bean;

import java.io.Serializable;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 包含状态的Bean实体<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-3<br>
 */
public class StatusBean implements Serializable {

    private static final long serialVersionUID = 1599511947453322174L;

    /**
     * 返回代码
     */
    private String code;

    /**
     * 返回描述
     */
    private String message;

    /**
     * 是否成功状态
     */
    private boolean success;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
