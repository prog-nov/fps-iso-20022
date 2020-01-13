package com.forms.beneform4j.util.param.enums.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.forms.beneform4j.util.param.enums.IEnumParam;
import com.forms.beneform4j.util.param.enums.IEnumParamItem;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 枚举参数实现<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-12<br>
 */
public class EnumParam implements IEnumParam {

    private static final long serialVersionUID = 1385030239468270262L;

    /**
     * 参数代码
     */
    private String paramCode;

    /**
     * 参数名称
     */
    private String paramName;

    /**
     * 参数组别
     */
    private String paramGroup;

    /**
     * 参数属性
     */
    private String paramAttr;

    /**
     * 是否可编辑
     */
    private boolean editable;

    /**
     * 序号
     */
    private int seqno;

    /**
     * 参数描述
     */
    private String des;

    /**
     * 数据项
     */
    private List<IEnumParamItem> items;

    /**
     * 键值映射
     */
    private Map<String, IEnumParamItem> map = new HashMap<String, IEnumParamItem>();

    @Override
    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }

    @Override
    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    @Override
    public String getParamGroup() {
        return paramGroup;
    }

    public void setParamGroup(String paramGroup) {
        this.paramGroup = paramGroup;
    }

    @Override
    public String getParamAttr() {
        return paramAttr;
    }

    public void setParamAttr(String paramAttr) {
        this.paramAttr = paramAttr;
    }

    @Override
    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    @Override
    public int getSeqno() {
        return seqno;
    }

    public void setSeqno(int seqno) {
        this.seqno = seqno;
    }

    @Override
    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public List<IEnumParamItem> getItems() {
        return items;
    }

    public void setItems(List<IEnumParamItem> items) {
        this.items = items;
    }

    @Override
    public IEnumParamItem getItem(String code) {
        if (map.isEmpty() && items != null && !items.isEmpty()) {
            synchronized (map) {
                if (map.isEmpty()) {
                    for (IEnumParamItem item : items) {
                        this.map.put(item.getDataCode(), item);
                    }
                }
            }
        }
        return this.map.get(code);
    }

}
