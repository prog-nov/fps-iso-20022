package com.forms.beneform4j.webapp.common.web;

import javax.servlet.http.HttpServletRequest;

import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.web.WebUtils;
import com.forms.beneform4j.webapp.authority.login.domain.LoginConst;
import com.forms.beneform4j.webapp.systemmanage.user.bean.UserInfo;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 描述<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-5<br>
 */
public class WebTool {

    /**
     * 获取登录用户
     * 
     * @return
     */
    public static UserInfo getLoginUser() {
        return (null != WebUtils.getSession()) ? (UserInfo) WebUtils.getSessionAttr(LoginConst.LOGIN_USER) : null;
    }

    /**
     * 从请求中获取用户
     * 
     * @param request
     * @return
     */
    public static UserInfo getLoginUser(HttpServletRequest request) {
        if (null == request) {
            return getLoginUser();
        }
        return (UserInfo) WebUtils.getSessionAttr(request.getSession(), LoginConst.LOGIN_USER);
    }

    /**
     * 从会话清除用户
     */
    public static void removeLoginUser() {
        if (null != WebUtils.getSession())
            WebUtils.removeSessionAttr(LoginConst.LOGIN_USER);
    }

    /**
     * 保存用户到会话
     * 
     * @param user
     */
    public static void saveUser(UserInfo user) {
        if (null != WebUtils.getSession())
            WebUtils.setSessionAttr(LoginConst.LOGIN_USER, user);
    }

    /**
     * 创建会话Id
     * 
     * @return
     */
    public static String buildSessionId() {
        return Tool.STRING.getRandomNumericIncludeTime(8);
    }

    /**
     * 保存会话Id
     * 
     * @param sessionId
     */
    public static void saveAutoGenerateSessionId(String sessionId) {
        if (null != WebUtils.getSession())
            WebUtils.setSessionAttr(LoginConst.SESSION_ID, sessionId);
    }

    /**
     * 获取会话Id
     * 
     * @return
     */
    public static String getAutoGenerateSessionId() {
        return (null != WebUtils.getSession()) ? (String) WebUtils.getSessionAttr(LoginConst.SESSION_ID) : null;
    }

}
