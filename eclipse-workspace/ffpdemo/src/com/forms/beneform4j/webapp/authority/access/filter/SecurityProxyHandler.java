package com.forms.beneform4j.webapp.authority.access.filter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.logger.CommonLogger;
import com.forms.beneform4j.security.core.access.info.IAuthorizationInfo;
import com.forms.beneform4j.security.core.common.exception.SecurityExceptionCodes;
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
     * 权限认证失败时的返回视图映射
     */
    private Map<String, String> viewMapping;

    /**
     * 权限认证失败时的默认视图
     */
    private String defaultView;

    /**
     * 权限认证失败时的返回视图映射
     */
    private Map<String, String> ajaxViewMapping;

    /**
     * 权限认证失败时的默认视图
     */
    private String ajaxDefaultView;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSupport(HttpServletRequest request) {
        return null != request.getAttribute(IAuthorizationInfo.class.getName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModelAndView handler(HttpServletRequest request) {
        IAuthorizationInfo info = (IAuthorizationInfo) request.getAttribute(IAuthorizationInfo.class.getName());
        request.removeAttribute(IAuthorizationInfo.class.getName());
        ModelAndView mv = new ModelAndView();
        if (this.isAjaxRequest(request)) {
            mv.addObject("success", info.isSuccess());
            Map<String, Object> data = new HashMap<String, Object>();
            mv.addObject("data", data);
            data.put("code", info.getCode());
            data.put("message", info.getMessage());
            mv.setViewName(getAuthView(info, getAjaxViewMapping(), getAjaxDefaultView()));
        } else {
            String code = info.getCode();
            try {
                HttpServletResponse response = ServletHelp.getResponse();
                if (SecurityExceptionCodes.NOT_FOUND_PERMISSION.equals(code)) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                } /*
                   * else if(SecurityExceptionCodes.NO_AUTHORITY.equals(code)){
                   * response.sendError(HttpServletResponse.SC_FORBIDDEN); }else
                   * if(SecurityExceptionCodes.SESSION_TIMEOUT.equals(code)){
                   * response.sendError(HttpServletResponse.SC_REQUEST_TIMEOUT); }else{
                   * response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); }
                   */
            } catch (Exception e) {
                e.printStackTrace();
            }
            mv.addObject(getAuthorizationInfoAttributeName(), info);
            mv.setViewName(getAuthView(info, getViewMapping(), getDefaultView()));
        }
        CommonLogger.warn("access deny [" + info.getCode() + "] : " + info.getMessage());
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

    /**
     * 获取权限认证视图
     * 
     * @param info 认证信息
     * @param viewMapping 返回码与视图的映射
     * @param defaultView 默认视图
     * @return 视图
     */
    protected String getAuthView(IAuthorizationInfo info, Map<String, String> viewMapping, String defaultView) {
        if (null != info) {
            String code = info.getCode();
            if (null != viewMapping) {
                String view = viewMapping.get(code);
                if (!CoreUtils.isBlank(view)) {
                    return view;
                }
            }
        }
        return defaultView;
    }

    public String getAuthorizationInfoAttributeName() {
        return authorizationInfoAttributeName;
    }

    public void setAuthorizationInfoAttributeName(String authorizationInfoAttributeName) {
        this.authorizationInfoAttributeName = authorizationInfoAttributeName;
    }

    public Map<String, String> getViewMapping() {
        return viewMapping;
    }

    public void setViewMapping(Map<String, String> viewMapping) {
        this.viewMapping = viewMapping;
    }

    public String getDefaultView() {
        return defaultView;
    }

    public void setDefaultView(String defaultView) {
        this.defaultView = defaultView;
    }

    public Map<String, String> getAjaxViewMapping() {
        return ajaxViewMapping;
    }

    public void setAjaxViewMapping(Map<String, String> ajaxViewMapping) {
        this.ajaxViewMapping = ajaxViewMapping;
    }

    public String getAjaxDefaultView() {
        return ajaxDefaultView;
    }

    public void setAjaxDefaultView(String ajaxDefaultView) {
        this.ajaxDefaultView = ajaxDefaultView;
    }
}
