package com.forms.beneform4j.webapp.authority.login.service.handler;

import javax.annotation.Resource;

import com.forms.beneform4j.core.util.rsa.IKeyService;
import com.forms.beneform4j.core.util.rsa.impl.KeyProperty;
import com.forms.beneform4j.security.core.login.handler.impl.UsernamePasswordTokenHandler;
import com.forms.beneform4j.security.core.login.info.IAuthenticationInfo;
import com.forms.beneform4j.security.core.login.token.impl.UsernamePasswordToken;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 密码解密处理器<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-11-<br>
 */
public class PasswordDecodeHandler extends UsernamePasswordTokenHandler {

    @Resource(name = "rsaKeyService")
    private IKeyService<KeyProperty> keyService;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doHandler(UsernamePasswordToken token, IAuthenticationInfo info) {
        token.setUserPwd(keyService.decrypt(token.getUserPwd()));
    }

}
