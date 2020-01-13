package com.forms.beneform4j.webapp.authority.login.service.handler;

import com.forms.beneform4j.security.SecurityUtils;
import com.forms.beneform4j.security.core.login.handler.impl.UsernamePasswordTokenHandler;
import com.forms.beneform4j.security.core.login.info.IAuthenticationInfo;
import com.forms.beneform4j.security.core.login.token.impl.UsernamePasswordToken;
import com.forms.beneform4j.security.core.login.user.IUser;
import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.webapp.authority.login.domain.LoginConst;
import com.forms.beneform4j.webapp.common.param.service.ParamHolder;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 用户锁定处理器<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-11<br>
 */
public class UserLockHandler extends UsernamePasswordTokenHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doHandler(UsernamePasswordToken token, IAuthenticationInfo info) {
        isLocked(token, info);
    }

    /**
     * 判断用户是否锁定
     * 
     * @param token
     * @param info
     */
    private void isLocked(UsernamePasswordToken token, IAuthenticationInfo info) {
        IUser user = info.getUser();
        if (LoginConst.LOCK.equals(user.getLockFlag())) {
            // 这里计算已经锁定的时间是否超过了解锁时间，来确定用户是否被锁定了
            int lockedTimes = ParamHolder.getParameter(LoginConst.USER_LOCKED_TIME, Integer.class);// 最少锁定时间
                                                                                                   // 3
                                                                                                   // 分钟
            if (lockedTimes != -1) {
                try {
                    if (System.currentTimeMillis() - Tool.DATE.getTime(user.getLockDate() + " " + user.getLockTime(), "yyyyMMdd hhmmss") <= lockedTimes * 60 * 1000) {
                        // 用户已被锁定
                        SecurityUtils.setSecurityCodeAndMessage(info, LoginConst.USER_TRY_TOO_MORE, Tool.LOCALE.getMessage("login.try.too_more", token.getUserId()));
                    }
                } catch (Throwable t) {
                }
            }
        }
    }

}
