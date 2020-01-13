package com.forms.beneform4j.webapp.authority.login.service.handler;

import com.forms.beneform4j.security.SecurityUtils;
import com.forms.beneform4j.security.core.login.handler.impl.UsernamePasswordTokenHandler;
import com.forms.beneform4j.security.core.login.info.IAuthenticationInfo;
import com.forms.beneform4j.security.core.login.token.impl.UsernamePasswordToken;
import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.web.servlet.ServletHelp;
import com.forms.beneform4j.webapp.authority.login.domain.LoginConst;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : IP控制处理器<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-11<br>
 */
public class UserIpLimitHandler extends UsernamePasswordTokenHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doHandler(UsernamePasswordToken token, IAuthenticationInfo info) {
        isAllowIp(token, info);
    }

    /**
     * Ip合法性检测
     * 
     * @param token
     * @param info
     */
    private void isAllowIp(UsernamePasswordToken token, IAuthenticationInfo info) {
        String remoteIp = ServletHelp.getRequestInfo().getRemoteIp();
        String ips = info.getUser().getLimitIp();
        boolean isAllow = false;
        if (!Tool.CHECK.isBlank(ips)) {
            String[] splitIps = ips.split(",");
            for (String configIp : splitIps) {
                if (Tool.STRING.safeEquals(configIp, remoteIp)) {
                    isAllow = true;
                    break;
                }
            }

            if (!isAllow) {
                SecurityUtils.setSecurityCodeAndMessage(info, LoginConst.USER_IP_ERROR, Tool.LOCALE.getMessage("login.ip.error", token.getUserId(), remoteIp));
            }
        }
    }

}
