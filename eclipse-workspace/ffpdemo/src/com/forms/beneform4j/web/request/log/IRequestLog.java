package com.forms.beneform4j.web.request.log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forms.beneform4j.core.service.request.IRequestInfo;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : Web请求日志<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-25<br>
 */
public interface IRequestLog {

    /**
     * 打印请求开始日志
     * 
     * @param request 请求对象
     * @param response 响应对象
     * @param info 请求信息
     */
    public void startRequestLog(HttpServletRequest request, HttpServletResponse response, IRequestInfo info);

    /**
     * 打印请求异常日志
     * 
     * @param request 请求对象
     * @param response 响应对象
     * @param info 请求信息
     * @param e 异常对象
     * @param endTime 结束时间
     */
    public void exceptionRequestLog(HttpServletRequest request, HttpServletResponse response, IRequestInfo info, Exception e, long endTime);

    /**
     * 打印请求结束日志
     * 
     * @param request 请求对象
     * @param response 响应对象
     * @param info 请求信息
     * @param endTime 结束时间
     */
    public void endRequestLog(HttpServletRequest request, HttpServletResponse response, IRequestInfo info, long endTime);
}
