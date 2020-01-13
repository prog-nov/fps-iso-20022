package com.forms.beneform4j.web.springmvc.handlermapping.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.forms.beneform4j.web.WebUtils;
import com.forms.beneform4j.web.springmvc.handlermapping.IProxyHandler;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 直接转发视图的请求<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-1<br>
 */
public class DirectViewnameProxyHandler implements IProxyHandler {

    private String suffix = "page";

    @Override
    public boolean isSupport(HttpServletRequest request) {
        String suffix = getSuffix();
        return request.getRequestURI().endsWith("." + suffix);
    }

    @Override
    public ModelAndView handler(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String requestUrl = request.getRequestURI();
        String view = WebUtils.getRestUriWithoutRoot(requestUrl);
        mv.setViewName(view);
        return mv;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
