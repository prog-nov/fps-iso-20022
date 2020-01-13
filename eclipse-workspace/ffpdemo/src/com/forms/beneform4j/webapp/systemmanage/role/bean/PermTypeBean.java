package com.forms.beneform4j.webapp.systemmanage.role.bean;

import java.io.Serializable;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 权限类型信息Bean对象<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-15<br>
 */
public class PermTypeBean implements Serializable {

    private static final long serialVersionUID = 8655489433413974983L;

    /**
     * 权限类型代码
     */
    private String permType;

    /**
     * 权限类型名称
     */
    private String permTypeName;

    /**
     * 权限类型参数
     */
    private String permTypeParam;

    private int seqno;

    private String des;

    public String getPermType() {
        return permType;
    }

    public void setPermType(String permType) {
        this.permType = permType;
    }

    public String getPermTypeName() {
        return permTypeName;
    }

    public void setPermTypeName(String permTypeName) {
        this.permTypeName = permTypeName;
    }

    public String getPermTypeParam() {
        return permTypeParam;
    }

    public void setPermTypeParam(String permTypeParam) {
        this.permTypeParam = permTypeParam;
    }

    public int getSeqno() {
        return seqno;
    }

    public void setSeqno(int seqno) {
        this.seqno = seqno;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
