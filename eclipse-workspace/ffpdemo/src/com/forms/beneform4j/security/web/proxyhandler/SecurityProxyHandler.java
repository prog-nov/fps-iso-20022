package com.forms.beneform4j.security.web.proxyhandler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.logger.CommonLogger;
import com.forms.beneform4j.security.core.access.info.IAuthorizationInfo;
import com.forms.beneform4j.web.WebUtils;
import com.forms.beneform4j.web.servlet.ServletHelp;
import com.forms.beneform4j.web.springmvc.handlermapping.IProxyHandler;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 安全认证失败后的实际处理<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-20<br>
 */
public class SecurityProxyHandler implements IProxyHandler {

    /**
     * 权限认证失败时，将认证信息存放于request中的属性名称
     */
    private String authorizationInfoAttributeName = "authorizationInfo";

    /**
     * 安全认证失败返回码与状态的关系映射
     */
    private Map<String, SecurityCodeStatusBean> codeStatusMapping;

    /**
     * 权限认证失败时的默认视图
     */
    private String defaultView;

    /**
     * 权限认证失败时的默认视图
     */
    private String ajaxDefaultView;

    @Override
    public boolean isSupport(HttpServletRequest request) {
        return null != request.getAttribute(IAuthorizationInfo.class.getName());
    }

    @Override
    public ModelAndView handler(HttpServletRequest request) {
        IAuthorizationInfo info = (IAuthorizationInfo) request.getAttribute(IAuthorizationInfo.class.getName());
        request.removeAttribute(IAuthorizationInfo.class.getName());
        String code = info.getCode();
        CommonLogger.warn("access deny [" + code + "] : " + info.getMessage());

        Map<String, SecurityCodeStatusBean> codeStatusMapping = getCodeStatusMapping();
        SecurityCodeStatusBean status = codeStatusMapping == null ? null : codeStatusMapping.get(code);

        if (isAjaxRequest(request)) {
            return doHandlerAjaxRequest(info, status);
        } else {
            return doHandlerNormalRequest(info, status);
        }
    }

    /**
     * 处理普通请求
     * 
     * @param info 权限认证返回信息
     * @param status 权限认证代码相关的状态配置
     * @return 返回视图
     */
    protected ModelAndView doHandlerNormalRequest(IAuthorizationInfo info, SecurityCodeStatusBean status) {
        ModelAndView mv = new ModelAndView();
        mv.addObject(getAuthorizationInfoAttributeName(), info);

        String view = status == null ? null : status.getView();
        if (CoreUtils.isBlank(view)) {
            view = getDefaultView();
        }
        mv.setViewName(view);

        if (null != status && 0 != status.getHttpStatus()) {
            try {
                HttpServletResponse response = ServletHelp.getResponse();
                response.sendError(status.getHttpStatus());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mv;
    }

    /**
     * 处理Ajax请求
     * 
     * @param info 权限认证返回信息
     * @param status 权限认证代码相关的状态配置
     * @return 返回视图
     */
    protected ModelAndView doHandlerAjaxRequest(IAuthorizationInfo info, SecurityCodeStatusBean status) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("success", info.isSuccess());
        Map<String, Object> data = new HashMap<String, Object>();
        mv.addObject("data", data);
        data.put("code", info.getCode());
        data.put("message", info.getMessage());
        String view = status == null ? null : status.getAjaxView();
        if (CoreUtils.isBlank(view)) {
            view = getAjaxDefaultView();
        }
        mv.setViewName(view);
        return mv;
    }

    /**
     * 是否为Ajax请求
     * 
     * @param request
     * @return
     */
    protected boolean isAjaxRequest(HttpServletRequest request) {
        return WebUtils.isAjaxRequest(request);
    }

    public String getAuthorizationInfoAttributeName() {
        return authorizationInfoAttributeName;
    }

    public void setAuthorizationInfoAttributeName(String authorizationInfoAttributeName) {
        this.authorizationInfoAttributeName = authorizationInfoAttributeName;
    }

    public Map<String, SecurityCodeStatusBean> getCodeStatusMapping() {
        return codeStatusMapping;
    }

    public void setCodeStatusMapping(Map<String, SecurityCodeStatusBean> codeStatusMapping) {
        this.codeStatusMapping = codeStatusMapping;
    }

    public String getDefaultView() {
        return defaultView;
    }

    public void setDefaultView(String defaultView) {
        this.defaultView = defaultView;
    }

    public String getAjaxDefaultView() {
        return ajaxDefaultView;
    }

    public void setAjaxDefaultView(String ajaxDefaultView) {
        this.ajaxDefaultView = ajaxDefaultView;
    }
}
