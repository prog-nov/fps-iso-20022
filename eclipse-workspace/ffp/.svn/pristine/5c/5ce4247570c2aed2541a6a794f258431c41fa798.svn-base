package com.forms.beneform4j.web.request;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.forms.beneform4j.core.service.request.IRequestInfo;
import com.forms.beneform4j.core.service.request.IRequestInfoFactory;
import com.forms.beneform4j.core.service.request.impl.BaseRequestInfo;
import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.track.Tracker;
import com.forms.beneform4j.web.UserAgent;
import com.forms.beneform4j.web.WebUtils;
import com.forms.beneform4j.web.servlet.ServletHelp;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 创建Web请求信息的工厂<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-25<br>
 */
public class WebRequestInfoFactory implements IRequestInfoFactory {

    private static final String REMOTE_BROWSER_SESSION_NAME = WebRequestInfoFactory.class.getName() + ".REMOTE_BROWSER";
    private static final String REMOTE_OS_SESSION_NAME = WebRequestInfoFactory.class.getName() + ".REMOTE_OS";

    /**
     * 创建Web请求信息
     */
    @Override
    public IRequestInfo getRequestInfo() {
        HttpServletRequest request = ServletHelp.getRequest();
        BaseRequestInfo webRequest = new BaseRequestInfo();
        if (!Tracker.isTracking()) {
            Tracker.start();
        }
        webRequest.setRequestId(Tracker.getCurrentTrackId());
        webRequest.setDate(new Date());
        webRequest.setTime(System.currentTimeMillis());
        webRequest.setRemoteIp(WebUtils.getClientIpAddress(request));
        webRequest.setProtocol(request.getProtocol());
        Map<String, String[]> params = request.getParameterMap();
        if (null != params) {
            for (String key : params.keySet()) {
                String[] value = params.get(key);
                if (null == value || 0 == value.length) {
                    webRequest.addParameters(key, null);
                } else if (1 == value.length) {
                    webRequest.addParameters(key, value[0]);
                } else {
                    webRequest.addParameters(key, value);
                }
            }
        }
        String uri = WebUtils.getUriWithoutRoot(request.getRequestURI());
        if (-1 != uri.indexOf('.')) {
            uri = uri.substring(0, uri.indexOf('.'));
        }
        webRequest.setRequestUrl(uri);
        handlerBrowserAndOperateSystem(request, webRequest);
        return webRequest;
    }

    private void handlerBrowserAndOperateSystem(HttpServletRequest request, BaseRequestInfo webRequest) {
        try {
            HttpSession session = request.getSession();
            String browser = null;
            String operateSystem = null;
            if (!WebUtils.isInvalidated(session)) {// 会话未失效
                browser = (String) session.getAttribute(REMOTE_BROWSER_SESSION_NAME);
                operateSystem = (String) session.getAttribute(REMOTE_OS_SESSION_NAME);
                if (CoreUtils.isBlank(browser)) {
                    UserAgent userAgent = new UserAgent(request.getHeader("User-Agent"), 0);
                    browser = userAgent.getBrowser();
                    operateSystem = userAgent.getOperateSystem();
                    session.setAttribute(REMOTE_BROWSER_SESSION_NAME, browser);
                    session.setAttribute(REMOTE_OS_SESSION_NAME, operateSystem);
                    webRequest.setRemoteBrowser(browser);
                    webRequest.setRemoteOperateSystem(operateSystem);
                } else {
                    webRequest.setRemoteBrowser(browser);
                    webRequest.setRemoteOperateSystem(operateSystem);
                }
            } else {
                UserAgent userAgent = new UserAgent(request.getHeader("User-Agent"), 0);
                webRequest.setRemoteBrowser(userAgent.getBrowser());
                webRequest.setRemoteOperateSystem(userAgent.getOperateSystem());
            }
        } catch (Exception e) {
        }
    }
}
