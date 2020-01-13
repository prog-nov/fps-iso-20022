package com.forms.beneform4j.webapp.authority.login.service.listener;

import javax.annotation.Resource;

import com.forms.beneform4j.core.service.request.IRequestInfo;
import com.forms.beneform4j.security.SecurityUtils;
import com.forms.beneform4j.security.core.login.info.IAuthenticationInfo;
import com.forms.beneform4j.security.core.login.listener.impl.LoginListenerSupport;
import com.forms.beneform4j.security.core.login.token.IAuthenticationToken;
import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.web.WebUtils;
import com.forms.beneform4j.web.servlet.ServletHelp;
import com.forms.beneform4j.webapp.authority.login.bean.BaseLoginLogBean;
import com.forms.beneform4j.webapp.authority.login.dao.ILoginDao;
import com.forms.beneform4j.webapp.authority.login.domain.LoginConst;
import com.forms.beneform4j.webapp.authority.session.LocalSessionStore;
import com.forms.beneform4j.webapp.common.web.WebTool;
import com.forms.beneform4j.webapp.systemmanage.user.bean.UserInfo;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 登录成功后处理<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-11<br>
 */
public class AfterLoginSuccessListener extends LoginListenerSupport {

    @Resource(name = "loginDao")
    private ILoginDao dao;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onLoginSuccess(IAuthenticationToken authenticationToken, IAuthenticationInfo info) {

        String sessionId = Tool.STRING.getRandomNumericIncludeTime(8);
        UserInfo user = (UserInfo) info.getUser();
        user.setUserStatus(LoginConst.AVAILABLE);
        user.setLastLoginIp(ServletHelp.getRequestInfo().getRemoteIp());
        user.setLastLoginDate(Tool.DATE.getDate());
        user.setLastLoginTime(Tool.DATE.getTime());
        user.setLoginNum(0);
        user.setSessionId(sessionId);
        user.setOnlineSessionNum(user.getOnlineSessionNum() + 1);

        // 更新用户表、写入用户日志表、写入在线用户表
        dao.dAfterLoginSuccess(user);
        BaseLoginLogBean loginLogger = getLoginLogger(user, LoginConst.TYPE_LOGIN);
        dao.dRecordLoginLog(loginLogger);
        dao.dSetUserOnline(loginLogger);
        saveUser(user, sessionId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onLoginException(IAuthenticationToken authenticationToken, IAuthenticationInfo info, Exception exception) {
        SecurityUtils.setSecurityCodeAndMessage(info, LoginConst.LOGIN_EXCEPTION, exception.getMessage());
    }

    /**
     * 组装日志信息
     * 
     * @param user
     * @param type
     * @return
     */
    private BaseLoginLogBean getLoginLogger(UserInfo user, String type) {
        IRequestInfo info = ServletHelp.getRequestInfo();
        BaseLoginLogBean log = new BaseLoginLogBean();
        log.setKeyId(Tool.STRING.getRandomNumeric(32));
        log.setSessionId(user.getSessionId());
        log.setUserId(user.getUserId());
        log.setClientIp(user.getLastLoginIp());
        log.setLioFlag(type);
        log.setOptDate(Tool.DATE.getDate());
        log.setOptTime(Tool.DATE.getTime());
        if (!Tool.CHECK.isEmpty(info)) {
            log.setBrowser(info.getRemoteBrowser());
            log.setOs(info.getRemoteOperateSystem());
            log.setServerIp(info.getRemoteIp());
        }
        return log;
    }

    /**
     * 保存用户到会话
     * 
     * @param user
     * @param sessionId
     */
    private void saveUser(UserInfo user, String sessionId) {
        /*
         * UserInfo sessionUser = new UserInfo(); sessionUser.setUserId(user.getUserId());
         * sessionUser.setUserName(user.getUserName()); sessionUser.setOrgName(user.getOrgName());
         * sessionUser.setPermissionManager(user.getPermissionManager());
         * sessionUser.setSessionId(sessionId); sessionUser.setParamService(user.getParamService());
         * sessionUser.setPermissionManager(user.getPermissionManager());
         */
        LocalSessionStore.save(sessionId, WebUtils.getSession());
        WebTool.saveUser(user);
        WebTool.saveAutoGenerateSessionId(sessionId);
    }

}
