package com.forms.beneform4j.webapp.systemmanage.param.define.bean;

import java.io.Serializable;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 参数定义<br>
 * Author : LiYun <br>
 * Version : 1.1.0 <br>
 * Since : 1.1.0 <br>
 * Date : 2017-3-23<br>
 */
public class ParamDefineBean implements Serializable {

    private static final long serialVersionUID = 3706767353935542172L;
    
    /**
     * 参数代码
     */
    private String paramCode;

    /**
     * 参数名称
     */
    private String paramName;

    /**
     * 数据类型
     */
    private String dataType;

    /**
     * 数据类型名称
     */
    private String dataTypeName;

    /**
     * 存储类型
     */
    private String storeType;

    /**
     * 存储类型名称
     */
    private String storeTypeName;

    /**
     * 参数组别
     */
    private String paramGroup;

    /**
     * 参数组别名称
     */
    private String paramGroupName;

    /**
     * 可否编辑
     */
    private String editable;

    /**
     * 可否编辑名称
     */
    private String editableName;

    /**
     * 默认值
     */
    private String defaultValue;

    /**
     * 取值规则
     */
    private String valueRule;

    /**
     * 取值规则参数
     */
    private String valueRuleParam;

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

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDataTypeName() {
        return dataTypeName;
    }

    public void setDataTypeName(String dataTypeName) {
        this.dataTypeName = dataTypeName;
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public String getStoreTypeName() {
        return storeTypeName;
    }

    public void setStoreTypeName(String storeTypeName) {
        this.storeTypeName = storeTypeName;
    }

    public String getParamGroup() {
        return paramGroup;
    }

    public void setParamGroup(String paramGroup) {
        this.paramGroup = paramGroup;
    }

    public String getParamGroupName() {
        return paramGroupName;
    }

    public void setParamGroupName(String paramGroupName) {
        this.paramGroupName = paramGroupName;
    }

    public String getEditable() {
        return editable;
    }

    public void setEditable(String editable) {
        this.editable = editable;
    }

    public String getEditableName() {
        return editableName;
    }

    public void setEditableName(String editableName) {
        this.editableName = editableName;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getValueRule() {
        return valueRule;
    }

    public void setValueRule(String valueRule) {
        this.valueRule = valueRule;
    }

    public String getValueRuleParam() {
        return valueRuleParam;
    }

    public void setValueRuleParam(String valueRuleParam) {
        this.valueRuleParam = valueRuleParam;
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
