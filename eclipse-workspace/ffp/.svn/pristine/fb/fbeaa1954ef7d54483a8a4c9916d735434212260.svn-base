package com.forms.beneform4j.webapp.authority.login.service.handler;

import javax.annotation.Resource;

import com.forms.beneform4j.security.SecurityUtils;
import com.forms.beneform4j.security.core.login.handler.impl.UsernamePasswordTokenHandler;
import com.forms.beneform4j.security.core.login.info.IAuthenticationInfo;
import com.forms.beneform4j.security.core.login.token.impl.UsernamePasswordToken;
import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.webapp.authority.login.dao.ILoginDao;
import com.forms.beneform4j.webapp.authority.login.domain.LoginConst;
import com.forms.beneform4j.webapp.common.param.service.ParamHolder;
import com.forms.beneform4j.webapp.systemmanage.user.bean.UserInfo;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 用户查找处理器<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-11-<br>
 */
public class FindUserHandler extends UsernamePasswordTokenHandler {

    @Resource(name = "loginDao")
    private ILoginDao dao;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doHandler(UsernamePasswordToken token, IAuthenticationInfo info) {
        UserInfo loginUser = dao.dGetLoginUser(token);
        if (null == loginUser) {
            SecurityUtils.setSecurityCodeAndMessage(info, LoginConst.USER_NOT_EXIST, Tool.LOCALE.getMessage("login.user.notexist", token.getUserId()));
            return;
        }
        info.setUser(loginUser);

        // 校验密码
        checkPassword(token, loginUser, info);

        if (info.isSuccess()) {
            isAviable(loginUser, info);
        }
    }

    /**
     * 校验密码
     * 
     * @param requestUser
     * @param user
     * @param info
     */
    private void checkPassword(UsernamePasswordToken requestUser, UserInfo user, IAuthenticationInfo info) {
        if (!Tool.STRING.safeEquals(user.getUserPwd(), Tool.STRING.getMd5(requestUser.getUserId() + requestUser.getUserPwd()))) {
            int hasTryTimes = user.getLoginNum() + 1;
            int maxTryTimes = ParamHolder.getParameter(LoginConst.USER_TRY_LOGIN_NUM, Integer.class);
            if (maxTryTimes > 1) {
                if (maxTryTimes <= hasTryTimes) {// 锁住用户
                    user.setLockFlag(LoginConst.LOCK);
                    user.setLockTime(Tool.DATE.getTime());
                    user.setLockDate(Tool.DATE.getDate());
                    user.setLeftTryLoginNum(maxTryTimes - hasTryTimes);
                    SecurityUtils.setSecurityCodeAndMessage(info, LoginConst.USER_TRY_TOO_MORE, Tool.LOCALE.getMessage("login.try.too_more", requestUser.getUserId()));
                } else {
                    user.setLockFlag(LoginConst.UN_LOCK);
                    user.setLeftTryLoginNum(maxTryTimes - hasTryTimes);
                    user.setLockTime(null);
                    user.setLockDate(null);
                    SecurityUtils.setSecurityCodeAndMessage(info, LoginConst.USER_PWD_ERROR, Tool.LOCALE.getMessage("login.auth.failure", requestUser.getUserId(), user.getLeftTryLoginNum()));
                }
                user.setLoginNum(hasTryTimes);
                dao.dAfterLoginFailure(user);
            } else {
                SecurityUtils.setSecurityCodeAndMessage(info, LoginConst.USER_PWD_ERROR, Tool.LOCALE.getMessage("login.auth.failure.nolimit", requestUser.getUserId()));
            }
        }
    }

    /**
     * 是否可用
     * 
     * @param user
     * @param info
     */
    private void isAviable(UserInfo user, IAuthenticationInfo info) {
        if (!Tool.STRING.safeEquals(user.getUserStatus(), LoginConst.AVAILABLE)) {
            SecurityUtils.setSecurityCodeAndMessage(info, LoginConst.USER_STATE_ERROR, Tool.LOCALE.getMessage("login.state.error", user.getUserId()));
        }
    }

}
