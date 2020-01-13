package com.forms.beneform4j.core.service.spring.component;

import java.util.Locale;

import com.forms.beneform4j.core.service.spring.SpringHelp;
import com.forms.beneform4j.core.util.locale.impl.DefaultLocaleMessageResolver;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 使用自定义Spring实现的国际化信息解析器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24<br>
 */
public class SpringLocaleMessageResolver extends DefaultLocaleMessageResolver {

    /**
     * Spring初始化后使用Spring国际化，Spring未初始化时使用父类国际化
     */
    @Override
    public String getMessage(String code, String defaultMessage, Locale locale, Object... args) {
        if (SpringHelp.hasInit()) {
            return SpringHelp.getApplicationContext().getMessage(code, args, defaultMessage, locale);
        } else {
            return super.getMessage(code, defaultMessage, locale, args);
        }
    }
}
