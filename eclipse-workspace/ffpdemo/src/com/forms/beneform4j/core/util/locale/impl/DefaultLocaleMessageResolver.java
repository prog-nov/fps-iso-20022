package com.forms.beneform4j.core.util.locale.impl;

import java.util.Locale;

import com.forms.beneform4j.core.util.locale.ILocaleMessageResolver;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 使用属性文件的简单国际化信息实现类，不支持嵌套<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public class DefaultLocaleMessageResolver extends SpringReloadableResourceBundleMessageSource implements ILocaleMessageResolver {

    @Override
    public String getMessage(String code, String defaultMessage, Locale locale, Object... args) {
        return this.getMessage(code, args, defaultMessage, locale);
    }
}
