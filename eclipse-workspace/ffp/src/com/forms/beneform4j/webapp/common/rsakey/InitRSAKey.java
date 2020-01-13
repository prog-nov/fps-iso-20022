package com.forms.beneform4j.webapp.common.rsakey;

import com.forms.beneform4j.core.service.spring.SpringHelp;
import com.forms.beneform4j.core.util.init.Init;
import com.forms.beneform4j.core.util.logger.CommonLogger;
import com.forms.beneform4j.core.util.rsa.IKeyService;
import com.forms.beneform4j.core.util.rsa.impl.KeyProperty;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 初始化系统密钥对<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-11<br>
 */
@Init
public class InitRSAKey {

    /**
     * 初始化秘钥
     */
    @SuppressWarnings("unchecked")
    public void init() {
        try {
            IKeyService<KeyProperty> service = SpringHelp.getBean(IKeyService.class);
            KeyProperty initedKey = service.initKey();
            CommonLogger.info(String.format("公钥和私钥初始化完毕 : %s:%s:%s", initedKey.getModule(), initedKey.getPublicEmpoent(), initedKey.getPrivateEmpoent()));
        } catch (Exception e) {
            CommonLogger.error("公钥和私钥初始化异常", e);
        }
    }

}
