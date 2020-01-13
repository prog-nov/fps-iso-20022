package com.forms.beneform4j.security.core.access.handler;

import com.forms.beneform4j.core.service.request.IRequestInfo;
import com.forms.beneform4j.security.core.access.info.IAuthorizationInfo;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 访问控制处理器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-8<br>
 */
public interface IAccessControlHandler {

    /**
     * 访问控制处理，可以做权限校验处理，如果授权失败，需要设置返回码和描述
     * 
     * @param requestInfo
     * @param info
     */
    public void handler(IRequestInfo requestInfo, IAuthorizationInfo info);
}
