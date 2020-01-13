package com.forms.beneform4j.web.request.log.impl;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forms.beneform4j.core.service.request.IRequestInfo;
import com.forms.beneform4j.core.util.logger.CommonLogger;
import com.forms.beneform4j.web.WebBeneform4jConfig;
import com.forms.beneform4j.web.request.log.IParamConvert;
import com.forms.beneform4j.web.request.log.IRequestLog;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 请求日志的简单实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-25<br>
 */
public class SimpleRequestLog implements IRequestLog {

    /**
     * 打印请求开始日志
     */
    @Override
    public void startRequestLog(HttpServletRequest request, HttpServletResponse response, IRequestInfo info) {
        StringBuffer sb = new StringBuffer().append("requestId=").append(info.getRequestId()).append(",requestUrl=").append(info.getRequestUrl()).append(",remoteIp=").append(info.getRemoteIp()).append(",remoteOs=").append(info.getRemoteOperateSystem()).append(",remoteBrowser=").append(info.getRemoteBrowser());
        try {
            Enumeration<String> a = request.getParameterNames();
            if (null != a) {
                IParamConvert convert = WebBeneform4jConfig.getParamConvert();
                while (a.hasMoreElements()) {
                    String name = a.nextElement();
                    String param = request.getParameter(name);
                    if (null != convert) {
                        param = convert.convert(name, param);
                    }
                    sb.append(",").append(name).append("=").append(param);
                }
            }
        } catch (Exception ignore) {
        }
        CommonLogger.info("the request start. ==> " + sb.toString());
    }

    /**
     * 打印请求异常日志
     */
    @Override
    public void exceptionRequestLog(HttpServletRequest request, HttpServletResponse response, IRequestInfo info, Exception e, long endTime) {
        StringBuffer sb = new StringBuffer().append("requestId=").append(info.getRequestId()).append(",requestUrl=").append(info.getRequestUrl());
        CommonLogger.info("the request failure in " + (endTime - info.getTime()) + " ms. ==> " + sb.toString(), e);
    }

    /**
     * 打印请求结束日志
     */
    @Override
    public void endRequestLog(HttpServletRequest request, HttpServletResponse response, IRequestInfo info, long endTime) {
        StringBuffer sb = new StringBuffer().append("requestId=").append(info.getRequestId()).append(",requestUrl=").append(info.getRequestUrl());
        CommonLogger.info("the request completed in " + (endTime - info.getTime()) + " ms. ==> " + sb.toString());
    }
}
