package com.forms.beneform4j.web.servlet.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forms.beneform4j.core.dao.mybatis.mapper.impl.DataStreamMapperMethodExecutor;
import com.forms.beneform4j.core.service.request.IRequestInfo;
import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.track.Tracker;
import com.forms.beneform4j.web.annotation.JsonBodySupport;
import com.forms.beneform4j.web.request.log.IRequestLog;
import com.forms.beneform4j.web.request.log.impl.RequestLogChain;
import com.forms.beneform4j.web.servlet.ServletHelp;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : Web请求过滤器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-25<br>
 */
public class RequestFilter extends AbstractSkipPathMatcherFilter {

    /**
     * 请求日志
     */
    private static final IRequestLog log = new RequestLogChain();

    /**
     * 执行过滤
     */
    @Override
    protected void executeFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        Tracker.start();
        ServletHelp.setRequestAndResponse(request, response);
        IRequestInfo info = ServletHelp.getRequestInfo();
        log.startRequestLog(request, response, info);
        try {
            filterChain.doFilter(request, response);
            log.endRequestLog(request, response, info, System.currentTimeMillis());
        } catch (Exception e) {
            log.exceptionRequestLog(request, response, info, e, System.currentTimeMillis());
            throw e;
        } finally {
            CoreUtils.clearThreadCache();
            JsonBodySupport.removeJsonBodyInfoFormContext();
            DataStreamMapperMethodExecutor.clearDataStreamReader();
            ServletHelp.remove();
            Tracker.stop();
        }
    }

    /**
     * 初始化
     */
    @Override
    protected void initFilterBean() throws ServletException {
        super.initFilterBean();
        ServletHelp.setApplication(super.getServletContext());
    }
}
