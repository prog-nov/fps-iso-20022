package com.forms.beneform4j.webapp.common.bean;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 下拉列表的数据bean<br>
 * Author : luow <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-5<br>
 */
public class ComboBoxBean {

    /**
     * 下拉组件Id
     */
    private String id;

    /**
     * 下拉组件显示文本
     */
    private String text;

    /**
     * 是否选中
     */
    private boolean selected = false;

    public ComboBoxBean() {}

    public ComboBoxBean(String id, String text) {
        super();
        this.id = id;
        this.text = text;
    }

    public ComboBoxBean(String id, String text, boolean selected) {
        super();
        this.id = id;
        this.text = text;
        this.selected = selected;
    }

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

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
