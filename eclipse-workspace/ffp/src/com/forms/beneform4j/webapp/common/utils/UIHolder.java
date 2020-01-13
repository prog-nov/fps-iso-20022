package com.forms.beneform4j.webapp.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.forms.beneform4j.security.core.login.user.IUser;
import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.util.param.IParam;
import com.forms.beneform4j.util.param.enums.IEnumParam;
import com.forms.beneform4j.util.param.enums.IEnumParamItem;
import com.forms.beneform4j.util.param.single.ISingleParam;
import com.forms.beneform4j.util.param.tree.ITreeParam;
import com.forms.beneform4j.util.param.tree.ITreeParamNode;
import com.forms.beneform4j.util.tree.ITreeNode;
import com.forms.beneform4j.web.WebBeneform4jConfig;
import com.forms.beneform4j.webapp.common.AppConfig;
import com.forms.beneform4j.webapp.common.param.service.ParamHolder;
import com.forms.beneform4j.webapp.common.web.WebTool;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : UI帮助类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-11<br>
 */
public class UIHolder {

    /**
     * 获取语言
     * 
     * @return
     */
    public static String getLocale(HttpServletRequest request) {
        String locale = null;
        IUser user = WebTool.getLoginUser(request);
        if (null != user && null != user.getParamService()) {
            locale = user.getParamService().get(AppConfig.getLocaleConfigName());
        }

        if (Tool.CHECK.isBlank(locale)) {
            locale = WebBeneform4jConfig.getLocaleResolver().getLocale().toString();
        }
        return locale;
    }

    /**
     * 获取主题
     * 
     * @return
     */
    public static String getTheme(HttpServletRequest request) {
        String theme = null;
        IUser user = WebTool.getLoginUser(request);
        if (null != user && null != user.getParamService()) {
            theme = user.getParamService().get(AppConfig.getThemeConfigName());
        }

        if (Tool.CHECK.isBlank(theme)) {
            theme = WebBeneform4jConfig.getDefaultTheme();
        }
        return theme;
    }

    /**
     * 是否调试模式
     * 
     * @return
     */
    public static boolean isDebug() {
        return "debug".equalsIgnoreCase(ParamHolder.getParameter(AppConfig.getDevModeConfigName()));
    }

    /**
     * 转换参数为EasyUI视图数据
     * 
     * @param param
     * @return
     */
    public static Object convertParam2EasyUIView(IParam param) {
        if (param instanceof ITreeParam) {
            List<ITreeNode> children = ((ITreeParam) param).getRoot().getChildren();
            List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
            for (ITreeNode node : children) {
                datas.add(convertTreeParamNode2EasyUIView((ITreeParamNode) node));
            }
            return datas;
        } else if (param instanceof IEnumParam) {
            List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
            for (IEnumParamItem item : ((IEnumParam) param).getItems()) {
                datas.add(convertEnumParamNode2EasyUIView(item));
            }
            return datas;
        } else if (param instanceof ISingleParam) {
            return convertSingleParamNode2EasyUIView((ISingleParam) param);
        } else {
            return param;
        }
    }

    /**
     * 结构转换
     * 
     * @param node
     * @return
     */
    private static Map<String, Object> convertTreeParamNode2EasyUIView(ITreeParamNode node) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("id", node.getDataCode());
        data.put("text", node.getDataText());
        data.put("state", node.isLeaf() ? "open" : "closed");
        data.put("param", node.getDataParam());
        if (!node.isLeaf()) {
            List<Map<String, Object>> children = new ArrayList<Map<String, Object>>();
            data.put("children", children);
            for (ITreeNode child : node.getChildren()) {
                children.add(convertTreeParamNode2EasyUIView((ITreeParamNode) child));
            }
        }
        return data;
    }

    /**
     * 结构转换
     * 
     * @param item
     * @return
     */
    private static Map<String, Object> convertEnumParamNode2EasyUIView(IEnumParamItem item) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("id", item.getDataCode());
        data.put("text", item.getDataText());
        data.put("param", item.getDataParam());
        return data;
    }

    /**
     * 结构转换
     * 
     * @param param
     * @return
     */
    private static Map<String, Object> convertSingleParamNode2EasyUIView(ISingleParam param) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("id", param.getParamCode());
        data.put("value", param.getParamValue());
        return data;
    }
}
