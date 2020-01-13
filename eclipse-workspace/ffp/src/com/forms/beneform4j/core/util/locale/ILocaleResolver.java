package com.forms.beneform4j.core.util.locale;

import java.util.Locale;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 国际化对象解析器接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public interface ILocaleResolver {

    /**
     * 根据环境获取国际化对象
     * 
     * @return 当前环境国际化对象
     */
    public Locale getLocale();

    /**
     * 设置当前环境国际化对象
     * 
     * @param locale 国际化对象
     */
    public void setLocale(Locale locale);
}
