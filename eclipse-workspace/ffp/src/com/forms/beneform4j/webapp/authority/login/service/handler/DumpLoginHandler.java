package com.forms.beneform4j.webapp.authority.login.service.handler;

import com.forms.beneform4j.security.SecurityUtils;
import com.forms.beneform4j.security.core.login.handler.impl.UsernamePasswordTokenHandler;
import com.forms.beneform4j.security.core.login.info.IAuthenticationInfo;
import com.forms.beneform4j.security.core.login.token.impl.UsernamePasswordToken;
import com.forms.beneform4j.security.core.login.user.IUser;
import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.webapp.authority.login.domain.LoginConst;
import com.forms.beneform4j.webapp.common.web.WebTool;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 重复登录处理器<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-11<br>
 */
public class DumpLoginHandler extends UsernamePasswordTokenHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doHandler(UsernamePasswordToken token, IAuthenticationInfo info) {
        isDumpLogin(token, info);
    }

    /**
     * 是否重复登录
     * 
     * @param token
     * @param info
     */
    private void isDumpLogin(UsernamePasswordToken token, IAuthenticationInfo info) {
        IUser loginUser = WebTool.getLoginUser();
        if (loginUser != null) {
            // if(!loginUser.getUserId().equals(info.getUser().getUserId())){
            SecurityUtils.setSecurityCodeAndMessage(info, LoginConst.USER_DUMP_LOGIN, Tool.LOCALE.getMessage("login.user.dump_login", token.getUserId()));
            // }

        }
    }
}
