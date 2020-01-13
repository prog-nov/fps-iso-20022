package com.forms.beneform4j.webapp.systemmanage.param.enumparam.bean;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 列表型参数数据Bean对象<br>
 * Author : OuLinhai <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-7-7<br>
 */
public class EnumParamDataBean {
    /**
     * 参数代码
     */
    private String paramCode;

    /**
     * 数据编码
     */
    private String dataCode;

    /**
     * 数据文本
     */
    private String dataText;

    /**
     * 数据参数
     */
    private String dataParam;

    /**
     * 序号
     */
    private int seqno;

    /**
     * 描述
     */
    private String des;

    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }

    public String getDataCode() {
        return dataCode;
    }

    public void setDataCode(String dataCode) {
        this.dataCode = dataCode;
    }

    public String getDataText() {
        return dataText;
    }

    public void setDataText(String dataText) {
        this.dataText = dataText;
    }

    public String getDataParam() {
        return dataParam;
    }

    public void setDataParam(String dataParam) {
        this.dataParam = dataParam;
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
