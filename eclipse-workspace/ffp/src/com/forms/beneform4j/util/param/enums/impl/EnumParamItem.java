package com.forms.beneform4j.util.param.enums.impl;

import com.forms.beneform4j.util.param.enums.IEnumParamItem;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 枚举参数数据项实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-12<br>
 */
public class EnumParamItem implements IEnumParamItem {

    private static final long serialVersionUID = 3176179373282230884L;

    /**
     * 参数代码
     */
    private String paramCode;

    /**
     * 获取数据项代码
     * 
     * @return 数据项代码
     */
    private String dataCode;

    /**
     * 数据项文本
     */
    private String dataText;

    /**
     * 数据项参数
     */
    private String dataParam;

    /**
     * 序号
     */
    private int seqno;

    /**
     * 数据项描述
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
