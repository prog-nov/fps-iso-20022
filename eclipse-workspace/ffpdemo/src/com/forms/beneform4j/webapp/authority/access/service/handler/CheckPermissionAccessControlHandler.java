package com.forms.beneform4j.webapp.authority.access.service.handler;

import java.util.Set;

import com.forms.beneform4j.core.service.request.IRequestInfo;
import com.forms.beneform4j.security.SecurityUtils;
import com.forms.beneform4j.security.core.access.handler.IAccessControlHandler;
import com.forms.beneform4j.security.core.access.info.IAuthorizationInfo;
import com.forms.beneform4j.security.core.access.permission.IPermission;
import com.forms.beneform4j.security.core.common.exception.SecurityExceptionCodes;
import com.forms.beneform4j.security.core.login.user.IUser;
import com.forms.beneform4j.webapp.common.web.WebTool;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 权限认证处理器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-11<br>
 */
public class CheckPermissionAccessControlHandler implements IAccessControlHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public void handler(IRequestInfo requestInfo, IAuthorizationInfo info) {
        IPermission permission = info.getPermission();
        if (null != permission) {
            int level = permission.getPermLevel();
            if (0 == level) {
                // 公共功能，不做其它校验
            } else {
                IUser user = WebTool.getLoginUser();
                if (null == user) {// 用户会话已失效
                    SecurityUtils.setSecurityCodeAndMessage(info, SecurityExceptionCodes.SESSION_TIMEOUT, requestInfo.getRequestUrl());
                } else if (1 == level) {
                    // 有效用户即可，不做其它校验
                } else {
                    Set<Integer> permissionIds = user.getPermissionManager().getPermissionIds();
                    if (null == permissionIds || !permissionIds.contains(permission.getPermId())) {
                        SecurityUtils.setSecurityInfo(info, SecurityExceptionCodes.NO_AUTHORITY, requestInfo.getRequestUrl());
                    }
                }
            }
        }
    }
}
