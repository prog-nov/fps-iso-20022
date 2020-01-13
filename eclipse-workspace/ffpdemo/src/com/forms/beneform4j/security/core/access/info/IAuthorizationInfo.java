package com.forms.beneform4j.security.core.access.info;

import com.forms.beneform4j.security.core.access.permission.IPermission;
import com.forms.beneform4j.security.core.common.info.ISecurityInfo;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 授权信息<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-8<br>
 */
public interface IAuthorizationInfo extends ISecurityInfo {

    /**
     * 获取权限
     * 
     * @return 权限
     */
    public IPermission getPermission();

    /**
     * 设置权限
     * 
     * @param permission
     */
    public void setPermission(IPermission permission);
}
