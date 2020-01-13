package com.forms.beneform4j.webapp.authority.logout.listener;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.forms.beneform4j.core.service.spring.SpringHelp;
import com.forms.beneform4j.web.WebUtils;
import com.forms.beneform4j.webapp.authority.login.domain.LoginConst;
import com.forms.beneform4j.webapp.authority.login.service.ILoginService;
import com.forms.beneform4j.webapp.common.param.service.ParamHolder;
import com.forms.beneform4j.webapp.systemmanage.user.bean.UserInfo;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 在线用户服务类<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-29<br>
 */
@Service
public class OnlineUserSessionService {

    public void updateLastActiveTime(ServletRequestEvent sre) {
        ServletRequest req = sre.getServletRequest();
        if (req instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) req;
            // 更新会话最后活动时间
            request.getSession().setAttribute(OnlineUserSessionListener.SESSION_LAST_VISIT_TIME_KEY, System.currentTimeMillis());
        }
    }

    public void destroySession(HttpSession session) {
        UserInfo loginUser = (UserInfo) WebUtils.getSessionAttr(session, LoginConst.LOGIN_USER);
        if (null != loginUser) {
            ILoginService service = SpringHelp.getBean(ILoginService.class);
            service.sLogout(loginUser.getUserId(), loginUser.getSessionId());
        }
    }

    public boolean isTimeout(HttpSession session) {
        Long lastActive = (Long) WebUtils.getSessionAttr(session, OnlineUserSessionListener.SESSION_LAST_VISIT_TIME_KEY);
        if (null != lastActive) {
            long lastActiveTime = lastActive;
            long currTime = System.currentTimeMillis();
            long timeout = ParamHolder.getParameter("USER_SESSION_TIMEOUT", Long.class) * 60 * 1000;
            return currTime - lastActiveTime >= timeout;
        }
        return true;
    }

}
