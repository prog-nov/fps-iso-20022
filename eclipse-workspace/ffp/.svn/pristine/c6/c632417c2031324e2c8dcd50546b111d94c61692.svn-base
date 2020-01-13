package com.forms.beneform4j.web.springmvc.handlermapping;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 代理处理器接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-20<br>
 */
public interface IProxyHandler {

    /**
     * 是否支持该请求
     * 
     * @param request 请求对象
     * @return
     */
    public boolean isSupport(HttpServletRequest request);

    /**
     * 实际处理，返回MVC视图
     * 
     * @param request 请求对象
     * @return
     */
    public ModelAndView handler(HttpServletRequest request);
}
