package com.forms.beneform4j.core.util.locale;

import java.util.Locale;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 国际化消息接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public interface ILocaleMessageResolver {

    /**
     * 国际化消息
     * 
     * @param code 国际化消息的key值
     * @param defaultMessage 默认信息
     * @param locale 国际化对象
     * @param args 占位符参数
     * @return 国际化后的消息
     */
    public String getMessage(String code, String defaultMessage, Locale locale, Object... args);
}
