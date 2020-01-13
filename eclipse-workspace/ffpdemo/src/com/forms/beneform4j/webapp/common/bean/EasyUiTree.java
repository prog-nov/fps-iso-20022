package com.forms.beneform4j.webapp.common.bean;

import java.util.List;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 定义树的结构与easyui里面的树结构相同<br>
 * Author : luow <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-27<br>
 */
public class EasyUiTree {

    /**
     * 节点Id
     */
    public String id;

    /**
     * 节点文本
     */
    public String text;

    /**
     * 节点状态打开/关闭
     */
    public String state = "open";// open,closed

    /**
     * 是否选中
     */
    public boolean checked = false;

    /**
     * 附加属性
     */
    public Object attributes;

    /**
     * 子节点
     */
    public List<EasyUiTree> children;

    /**
     * 节点样式
     */
    public String iconCls;

    /**
     * 父节点Id
     */
    public String pid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Object getAttributes() {
        return attributes;
    }

    public void setAttributes(Object attributes) {
        this.attributes = attributes;
    }

    public List<EasyUiTree> getChildren() {
        return children;
    }

    public void setChildren(List<EasyUiTree> children) {
        this.children = children;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
