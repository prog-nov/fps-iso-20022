package com.forms.beneform4j.util.toolimpl;

import java.util.Locale;

import com.forms.beneform4j.core.util.CoreUtils;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 本地化工具类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-2-23<br>
 */
public abstract class LocaleUtilsImpl {

    private static final LocaleUtilsImpl instance = new LocaleUtilsImpl() {};

    private LocaleUtilsImpl() {}

    /**
     * 获取单实例
     * 
     * @return
     */
    public static LocaleUtilsImpl getInstance() {
        return instance;
    }

    /**
     * 根据代码和参数国际化信息
     * 
     * @param code 国际化信息
     * @param args 占位符参数
     * @return 国际化后的字符串
     */
    public String getMessage(String code, Object... args) {
        return CoreUtils.getMessage(code, args);
    }

    /**
     * 根据代码和参数国际化信息
     * 
     * @param code 国际化信息
     * @param locale 国际化对象
     * @param args 占位符参数
     * @return 国际化后的字符串
     */
    public String getMessage(String code, Locale locale, Object... args) {
        return CoreUtils.getMessage(code, locale, args);
    }

    /**
     * 根据代码和参数国际化信息
     * 
     * @param code 国际化信息
     * @param defaultMessage 默认消息
     * @param locale 国际化对象
     * @param args 占位符参数
     * @return 国际化后的字符串
     */
    public String getMessage(String code, String defaultMessage, Locale locale, Object... args) {
        return CoreUtils.getMessage(code, defaultMessage, locale, args);
    }

    /**
     * 获取当前Locale对象
     * 
     * @return Locale对象
     */
    public Locale getCurrentLocale() {
        return CoreUtils.getCurrentLocale();
    }

    /**
     * 设置当前国际化对象
     * 
     * @param locale 国际化对象
     */
    public void setCurrentLocale(Locale locale) {
        CoreUtils.setCurrentLocale(locale);
    }

    /**
     * 将字符串解析为Locale对象
     * 
     * @param str locale字符串
     * @return Locale对象
     */
    public Locale toLocale(String str) {
        return CoreUtils.toLocale(str);
    }
}
