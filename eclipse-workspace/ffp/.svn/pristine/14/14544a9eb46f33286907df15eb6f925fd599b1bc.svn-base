package com.forms.beneform4j.util.param.tree.impl;

import java.util.List;

import com.forms.beneform4j.util.param.tree.ITreeParam;
import com.forms.beneform4j.util.param.tree.ITreeParamNode;
import com.forms.beneform4j.util.tree.impl.Tree;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 树型参数实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-12<br>
 */
public class TreeParam extends Tree<ITreeParamNode> implements ITreeParam {

    /**
     * 
     */
    private static final long serialVersionUID = 1352641506604323455L;

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
     * 节点集
     */
    private List<ITreeParamNode> items;

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

    public List<ITreeParamNode> getItems() {
        return items;
    }

    public void setItems(List<ITreeParamNode> items) {
        this.items = items;
    }

    @Override
    public void build() {
        super.build(getItems(), getParamCode(), getParamName());
    }
}
