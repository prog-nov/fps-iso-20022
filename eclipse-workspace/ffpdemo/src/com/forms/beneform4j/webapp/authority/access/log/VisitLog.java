package com.forms.beneform4j.webapp.authority.access.log;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;

import com.forms.beneform4j.core.service.request.IRequestInfo;
import com.forms.beneform4j.core.util.config.BaseConfig;
import com.forms.beneform4j.core.util.env.EnvConsts;
import com.forms.beneform4j.security.core.access.info.IAuthorizationInfo;
import com.forms.beneform4j.security.core.access.permission.IPermission;
import com.forms.beneform4j.security.core.common.pathmatcher.PathMatcherSupport;
import com.forms.beneform4j.security.core.login.user.IUser;
import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.web.WebBeneform4jConfig;
import com.forms.beneform4j.web.request.log.IParamConvert;
import com.forms.beneform4j.web.request.log.IRequestLog;
import com.forms.beneform4j.webapp.authority.access.dao.IAccessControlDao;
import com.forms.beneform4j.webapp.authority.access.filter.SecurityFilter;
import com.forms.beneform4j.webapp.common.web.WebTool;
import com.forms.beneform4j.webapp.systemmanage.user.bean.UserInfo;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 访问日志<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-17<br>
 */
public class VisitLog extends PathMatcherSupport implements IRequestLog, InitializingBean {

    /**
     * 执行服务
     * 
     * @since 1.0.1
     */
    private ExecutorService executor;

    @Resource(name = "accessControlDao")
    private IAccessControlDao dao;

    public VisitLog() {
        setMatcherWhenUrlPatternsIsEmpty(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        if (null == this.executor) {
            executor = Executors.newCachedThreadPool();
        }
    }

    /**
     * 注入执行服务
     * 
     * @param executor
     */
    public void setExecutor(ExecutorService executor) {
        this.executor = executor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startRequestLog(HttpServletRequest request, HttpServletResponse response, IRequestInfo info) {}

    /**
     * {@inheritDoc}
     */
    @Override
    public void exceptionRequestLog(HttpServletRequest request, HttpServletResponse response, final IRequestInfo info, Exception e, final long endTime) {
        if (!super.isMatcher(info.getRequestUrl())) {
            return;
        }
        final IAuthorizationInfo ai = (IAuthorizationInfo) request.getAttribute(SecurityFilter.AUTHORIZATION_INFO_ATTRIBUTE_NAME);
        UserInfo user = WebTool.getLoginUser(request);
        if (null == ai || null == ai.getPermission() || 2 != ai.getPermission().getPermLevel() || null == user) {// 无需授权或会话超时的，暂不写日志
            return;
        }
        final String optParams = getOptParams(request);
        final String userId = user.getUserId();
        final String sessionId = user.getSessionId();
        final String optPath = getOptPath(user, ai.getPermission());
        final String optFlag = "2";// 出现异常
        submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                try {
                    writeLog(info, endTime, ai, userId, sessionId, optPath, optParams, optFlag);
                } catch (Exception ignore) {
                }
                return null;
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endRequestLog(HttpServletRequest request, HttpServletResponse response, final IRequestInfo info, final long endTime) {
        if (!super.isMatcher(info.getRequestUrl())) {
            return;
        }
        final IAuthorizationInfo ai = (IAuthorizationInfo) request.getAttribute(SecurityFilter.AUTHORIZATION_INFO_ATTRIBUTE_NAME);
        UserInfo user = WebTool.getLoginUser(request);
        if (null == ai || null == ai.getPermission() || 2 != ai.getPermission().getPermLevel() || null == user) {// 无需授权或会话超时的，暂不写日志
            return;
        }
        final String optParams = getOptParams(request);
        final String userId = user.getUserId();
        final String sessionId = user.getSessionId();
        final String optPath = getOptPath(user, ai.getPermission());
        final String optFlag = ai.isSuccess() ? "1" : "0";
        submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                try {
                    writeLog(info, endTime, ai, userId, sessionId, optPath, optParams, optFlag);
                } catch (Exception ignore) {
                }
                return null;
            }
        });
    }

    /**
     * 写日志
     * 
     * @param info
     * @param endTime
     * @param ai
     * @param userId
     * @param sessionId
     * @param optPath
     * @param optParams
     * @param optFlag
     */
    private void writeLog(IRequestInfo info, long endTime, IAuthorizationInfo ai, String userId, String sessionId, String optPath, String optParams, String optFlag) {
        IPermission permission = ai.getPermission();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("keyId", Tool.STRING.getRandomKeyId());
        map.put("requestId", info.getRequestId());
        map.put("sessionId", sessionId);
        map.put("userId", userId);
        map.put("permId", null == permission ? null : permission.getPermId());
        map.put("clientIp", info.getRemoteIp());
        map.put("serverIp", EnvConsts.LOCALE_HOST);
        map.put("browser", info.getRemoteBrowser());
        map.put("os", info.getRemoteOperateSystem());
        map.put("optPath", optPath);
        map.put("optUrl", info.getRequestUrl());
        map.put("optParams", optParams);
        Date date = info.getDate();
        map.put("optDate", Tool.DATE.getFormatDate(date, BaseConfig.getDateFormat()));
        map.put("optTime", Tool.DATE.getFormatDate(date, BaseConfig.getTimeFormat()));
        map.put("costTime", endTime - info.getTime());
        map.put("optFlag", optFlag);
        dao.writeVisitLog(map);
    }

    /**
     * 获取路径
     * 
     * @param user
     * @param permission
     * @return
     */
    protected String getOptPath(IUser user, IPermission permission) {
        try {
            return user.getPermissionManager().getMenuTree().getNode(permission.getPermTypeKey()).getPath();
        } catch (Exception ignore) {
        }
        return "";
    }

    /**
     * 获取操作参数
     * 
     * @param request
     * @return
     */
    protected String getOptParams(HttpServletRequest request) {
        try {
            StringBuilder sb = new StringBuilder();
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
                if (sb.length() > 251) {
                    return sb.substring(1, 251);
                } else {
                    return sb.substring(1);
                }
            }
        } catch (Exception ignore) {
        }
        return "";
    }

    protected <T> Future<T> submit(Callable<T> task) {
        return this.executor.submit(task);
    }
}
