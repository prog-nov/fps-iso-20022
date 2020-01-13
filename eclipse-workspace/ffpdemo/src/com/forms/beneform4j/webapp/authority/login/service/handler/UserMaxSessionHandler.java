package com.forms.beneform4j.webapp.authority.login.service.handler;

import javax.annotation.Resource;

import com.forms.beneform4j.security.SecurityUtils;
import com.forms.beneform4j.security.core.login.handler.impl.UsernamePasswordTokenHandler;
import com.forms.beneform4j.security.core.login.info.IAuthenticationInfo;
import com.forms.beneform4j.security.core.login.token.impl.UsernamePasswordToken;
import com.forms.beneform4j.security.core.login.user.IUser;
import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.webapp.authority.login.dao.ILoginDao;
import com.forms.beneform4j.webapp.authority.login.domain.LoginConst;
import com.forms.beneform4j.webapp.common.param.service.ParamHolder;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 控制用户最大会话数<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-7-22<br>
 */
public class UserMaxSessionHandler extends UsernamePasswordTokenHandler {

    @Resource(name = "loginDao")
    private ILoginDao dao;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doHandler(UsernamePasswordToken token, IAuthenticationInfo info) {
        IUser user = info.getUser();
        if (null != user) {
            int onlineSessionNum = dao.dGetSessionNum(user.getUserId());
            int max = ParamHolder.getParameter("USER_MAX_SESSION_NUM", Integer.class);
            if (max != -1) {
                if (onlineSessionNum >= max) {
                    SecurityUtils.setSecurityCodeAndMessage(info, LoginConst.USER_SESSION_TOOMANY, Tool.LOCALE.getMessage("login.session.toomany", token.getUserId(), max));
                }
            }
        }

    }

}
