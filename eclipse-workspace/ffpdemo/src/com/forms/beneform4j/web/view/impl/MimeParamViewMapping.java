package com.forms.beneform4j.web.view.impl;

import java.util.Map;

import com.forms.beneform4j.core.service.request.IRequestInfo;
import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.web.servlet.ServletHelp;
import com.forms.beneform4j.web.view.IViewMapping;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 使用特定请求参数实现的默认视图映射<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-25<br>
 */
public class MimeParamViewMapping implements IViewMapping {

    /**
     * 表示mime参数的名称
     */
    private String mimeParamName = "mime";

    /**
     * mime及其视图的映射
     */
    private Map<String, String> mimeMapping;

    /**
     * 获取默认视图名称
     */
    @Override
    public String getViewname(IRequestInfo requestInfo) {
        String mime = ServletHelp.getRequest().getParameter(getMimeParamName());
        if (!CoreUtils.isBlank(mime)) {
            Map<String, String> mapping = getMimeMapping();
            if (null != mapping) {
                String view = mapping.get(mime);
                if (!CoreUtils.isBlank(view)) {
                    return view;
                }
            }
        }
        return null;
    }

    /**
     * 覆盖用户返回视图
     */
    @Override
    public boolean overrideUserView() {
        return true;
    }

    public String getMimeParamName() {
        return mimeParamName;
    }

    public void setMimeParamName(String mimeParamName) {
        this.mimeParamName = mimeParamName;
    }

    public Map<String, String> getMimeMapping() {
        return mimeMapping;
    }

    public void setMimeMapping(Map<String, String> mimeMapping) {
        this.mimeMapping = mimeMapping;
    }
}
