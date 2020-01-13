package com.forms.beneform4j.webapp.authority.logout.listener;

import java.util.List;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.forms.beneform4j.core.service.spring.SpringHelp;
import com.forms.beneform4j.webapp.common.AppConfig;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 销毁失效/超时会话<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-5<br>
 */
public class OnlineUserSessionListener implements HttpSessionListener, ServletRequestListener {

    /**
     * 保存会话最后活动时间的键值
     */
    protected static final String SESSION_LAST_VISIT_TIME_KEY = OnlineUserSessionListener.class.getName() + ".SESSION_LAST_VISIT_TIME_KEY";

    private PathMatcher pathMatcher = new AntPathMatcher();

    /**
     * 会话创建事件，保留会话
     * 
     * @param se
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        destroySession(se.getSession());
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {}

    /**
     * 请求初始化，更新对应会话的最后访问时间
     * 
     * @param sre
     */
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
        OnlineUserSessionService service = getService();
        if (null != service && !isMatcher(req.getRequestURI(), AppConfig.getUnUpdateSessionUrlPatterns())) {
            service.updateLastActiveTime(sre);
        }
    }

    /**
     * 销毁会话
     * 
     * @param session
     */
    private void destroySession(HttpSession session) {
        OnlineUserSessionService service = getService();
        if (null != service) {
            service.destroySession(session);
        }
    }

    private OnlineUserSessionService getService() {
        return SpringHelp.getBean(OnlineUserSessionService.class);
    }

    /**
     * Url排除过滤
     * 
     * @param url
     * @param configUrls
     * @return
     */
    private boolean isMatcher(String url, List<String> configUrls) {
        if (null != configUrls && !configUrls.isEmpty()) {
            for (String pattern : configUrls) {
                if (pathMatcher.match(pattern, url)) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

}
