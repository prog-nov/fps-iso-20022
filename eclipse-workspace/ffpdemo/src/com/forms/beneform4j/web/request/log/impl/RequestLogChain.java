package com.forms.beneform4j.web.request.log.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forms.beneform4j.core.service.request.IRequestInfo;
import com.forms.beneform4j.web.WebBeneform4jConfig;
import com.forms.beneform4j.web.request.log.IRequestLog;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 请求日志链<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-25<br>
 */
public class RequestLogChain implements IRequestLog {

    /**
     * 打印请求开始日志
     */
    @Override
    public void startRequestLog(final HttpServletRequest request, final HttpServletResponse response, final IRequestInfo info) {
        List<IRequestLog> requestLogs = WebBeneform4jConfig.getRequestLogs();
        if (null != requestLogs && !requestLogs.isEmpty()) {
            for (IRequestLog log : requestLogs) {// 顺序打印开始日志
                try {
                    log.startRequestLog(request, response, info);
                } catch (Throwable ignore) {// 忽略日志本身的异常
                }
            }
        }
    }

    @Override
    public void exceptionRequestLog(HttpServletRequest request, HttpServletResponse response, IRequestInfo info, Exception e, long endTime) {
        List<IRequestLog> requestLogs = WebBeneform4jConfig.getRequestLogs();
        if (null != requestLogs && !requestLogs.isEmpty()) {
            for (IRequestLog log : requestLogs) {// 顺序打印异常日志
                try {
                    log.exceptionRequestLog(request, response, info, e, endTime);
                } catch (Throwable ignore) {// 忽略日志本身的异常
                }
            }
        }

    }

    /**
     * 打印请求结束日志
     */
    @Override
    public void endRequestLog(final HttpServletRequest request, final HttpServletResponse response, final IRequestInfo info, final long endTime) {
        List<IRequestLog> requestLogs = WebBeneform4jConfig.getRequestLogs();
        if (null != requestLogs && !requestLogs.isEmpty()) {
            for (int i = requestLogs.size() - 1; i >= 0; i--) {// 逆序打印结束日志
                try {
                    requestLogs.get(i).endRequestLog(request, response, info, endTime);
                } catch (Throwable ignore) {// 忽略日志本身的异常
                }
            }
        }
    }
}
