package com.forms.beneform4j.web.view;

import java.util.ArrayList;
import java.util.List;

import com.forms.beneform4j.core.service.request.IRequestInfo;
import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.init.Init;
import com.forms.beneform4j.web.WebBeneform4jConfig;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 请求默认视图映射帮助类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-11<br>
 */
@Init
public class ViewMappingHolder {

    /**
     * 覆盖用户返回视图的映射
     */
    private static final List<IViewMapping> overrideViewMapping = new ArrayList<IViewMapping>();

    /**
     * 默认的视图返回映射
     */
    private static final List<IViewMapping> defaultViewMapping = new ArrayList<IViewMapping>();

    /**
     * 初始化
     */
    public void init() {
        this.initOverrideViewMapping();
        this.initViewMapping();
    }

    /**
     * 获取覆盖用户视图的视图
     * 
     * @param info 请求信息
     * @return 逻辑视图
     */
    public static String getOverrideView(IRequestInfo info) {
        if (!overrideViewMapping.isEmpty()) {
            for (IViewMapping viewMapping : overrideViewMapping) {
                String view = viewMapping.getViewname(info);
                if (!CoreUtils.isBlank(view)) {
                    return view;
                }
            }
        }
        return null;
    }

    /**
     * 获取默认视图
     * 
     * @param info 请求信息
     * @return 逻辑视图
     */
    public static String getDefaultView(IRequestInfo info) {
        if (!defaultViewMapping.isEmpty()) {
            for (IViewMapping viewMapping : defaultViewMapping) {
                String view = viewMapping.getViewname(info);
                if (!CoreUtils.isBlank(view)) {
                    return view;
                }
            }
        }
        return null;
    }

    /**
     * 初始化默认返回视图映射（覆盖用户返回视图）
     */
    private void initOverrideViewMapping() {
        List<IViewMapping> viewMappings = WebBeneform4jConfig.getViewMappings();
        if (null != viewMappings) {
            for (IViewMapping viewMapping : viewMappings) {
                if (viewMapping.overrideUserView()) {
                    overrideViewMapping.add(viewMapping);
                }
            }
        }
    }

    /**
     * 初始化默认返回视图映射
     */
    private void initViewMapping() {
        List<IViewMapping> viewMappings = WebBeneform4jConfig.getViewMappings();
        if (null != viewMappings) {
            for (IViewMapping viewMapping : viewMappings) {
                if (!viewMapping.overrideUserView()) {
                    defaultViewMapping.add(viewMapping);
                }
            }
        }
    }
}
