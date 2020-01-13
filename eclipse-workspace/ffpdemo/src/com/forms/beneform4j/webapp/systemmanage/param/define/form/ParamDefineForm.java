package com.forms.beneform4j.webapp.systemmanage.param.define.form;

import com.forms.beneform4j.webapp.common.form.BaseForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 参数定义<br>
 * Author : LiYun <br>
 * Version : 1.1.0 <br>
 * Since : 1.1.0 <br>
 * Date : 2017-3-23<br>
 */
public class ParamDefineForm extends BaseForm {

    private static final long serialVersionUID = 4465791086111695139L;
    
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
     * 存储类型
     */
    private String storeType;

    /**
     * 参数组别
     */
    private String paramGroup;

    /**
     * 可否编辑
     */
    private String editable;

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

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public String getParamGroup() {
        return paramGroup;
    }

    public void setParamGroup(String paramGroup) {
        this.paramGroup = paramGroup;
    }

    public String getEditable() {
        return editable;
    }

    public void setEditable(String editable) {
        this.editable = editable;
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

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
