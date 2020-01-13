package com.forms.beneform4j.util.json.serial.wrapper.impl;

import java.util.Map;

import com.forms.beneform4j.core.util.page.PageUtils;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 分页查询返回结果JSON包装器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-17<br>
 */
public class PageJsonWrapper extends MapJsonWrapper {

    /**
     * 表示分页查询返回JSON结果中总记录数的JSON属性名称，默认为total
     */
    private String totalRecordsJsonPropertyName = "total";

    public PageJsonWrapper() {
        super.setDataJsonPropertyName("rows");
    }

    @Override
    protected void doWrap(Map<String, Object> wrapper, Object original) {
        wrapper.put(getTotalRecordsJsonPropertyName(), getTotalRecords());
    }

    protected long getTotalRecords() {
        return PageUtils.getPage().getTotalRecords();
    }

    public String getTotalRecordsJsonPropertyName() {
        return totalRecordsJsonPropertyName;
    }

    public void setTotalRecordsJsonPropertyName(String totalRecordsJsonPropertyName) {
        this.totalRecordsJsonPropertyName = totalRecordsJsonPropertyName;
    }
}
