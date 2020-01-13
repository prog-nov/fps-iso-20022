package com.forms.beneform4j.core.util.encrypt.impl;

import com.forms.beneform4j.core.util.config.BaseConfig;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 使用配置的密钥实现的加解密<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public class ConfigKeyEncrypt extends BaseEncrypt {

    /**
     * 使用固定的Beneform4j配置的密钥
     */
    @Override
    protected String getEncryptKey(String key) {
        return BaseConfig.getEncryptKey();
    }
}
