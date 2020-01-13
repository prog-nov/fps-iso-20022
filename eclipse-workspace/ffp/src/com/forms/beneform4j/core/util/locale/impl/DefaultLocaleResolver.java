package com.forms.beneform4j.core.util.locale.impl;

import java.util.Locale;

import com.forms.beneform4j.core.util.env.EnvConsts;
import com.forms.beneform4j.core.util.locale.ILocaleResolver;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 返回默认国际化对象的实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public class DefaultLocaleResolver implements ILocaleResolver {

    /**
     * 返回环境中默认的国际化对象
     */
    @Override
    public Locale getLocale() {
        return EnvConsts.SYSTEM_LOCALE;
    }

    /**
     * 设置国际化对象
     */
    @Override
    public void setLocale(Locale locale) {
        if (null != locale) {
            Locale.setDefault(locale);
        }
    }
}
