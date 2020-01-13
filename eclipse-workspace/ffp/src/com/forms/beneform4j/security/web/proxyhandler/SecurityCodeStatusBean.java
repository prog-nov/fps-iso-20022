package com.forms.beneform4j.security.web.proxyhandler;

import java.io.Serializable;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 安全认证失败返回码与状态的关系<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-10-11<br>
 */
public class SecurityCodeStatusBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 7440779692067870455L;

    /**
     * 认证返回状态码
     */
    private String code;

    /**
     * 正常请求的返回视图
     */
    private String view;

    /**
     * Ajax请求的返回视图
     */
    private String ajaxView;

    /**
     * HTTP状态码
     */
    private int httpStatus;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getAjaxView() {
        return ajaxView;
    }

    public void setAjaxView(String ajaxView) {
        this.ajaxView = ajaxView;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }
}
