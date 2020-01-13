package com.forms.beneform4j.webapp.authority.access.service.handler;

import com.forms.beneform4j.core.service.request.IRequestInfo;
import com.forms.beneform4j.security.SecurityUtils;
import com.forms.beneform4j.security.core.access.handler.IAccessControlHandler;
import com.forms.beneform4j.security.core.access.info.IAuthorizationInfo;
import com.forms.beneform4j.security.core.access.permission.IPermission;
import com.forms.beneform4j.security.core.access.request.IRequestInfoPermissionMapping;
import com.forms.beneform4j.security.core.common.exception.SecurityExceptionCodes;
import com.forms.beneform4j.webapp.common.param.service.ParamHolder;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 权限认证处理器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-11<br>
 */
public class FindPermissionAccessControlHandler implements IAccessControlHandler {

    /**
     * 请求权限映射
     */
    private IRequestInfoPermissionMapping requestInfoPermissionMapping;

    /**
     * 查找权限处理
     */
    @Override
    public void handler(IRequestInfo requestInfo, IAuthorizationInfo info) {
        IRequestInfoPermissionMapping requestInfoPermissionMapping = getRequestInfoPermissionMapping();
        IPermission permission = requestInfoPermissionMapping.lookup(requestInfo);
        if (null == permission) {
            String permissionMode = ParamHolder.getParameter("PERMISSION_MODE");
            if ("MENU".equalsIgnoreCase(permissionMode)) {
                /**
                 * 如果系统参数中的权限校验级别为菜单级别，也即可以看见菜单就可以访问的话，并且找不到权限，这里就不再做进一步校验
                 */
            } else {
                SecurityUtils.setSecurityCodeAndMessage(info, SecurityExceptionCodes.NOT_FOUND_PERMISSION, requestInfo.getRequestUrl());
            }
        } else {
            info.setPermission(permission);
        }
    }

    public IRequestInfoPermissionMapping getRequestInfoPermissionMapping() {
        return requestInfoPermissionMapping;
    }

    public void setRequestInfoPermissionMapping(IRequestInfoPermissionMapping requestInfoPermissionMapping) {
        this.requestInfoPermissionMapping = requestInfoPermissionMapping;
    }
}
