package com.forms.beneform4j.util.toolimpl;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.util.Tool;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 检验工具类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-2-23<br>
 */
public abstract class CheckUtilsImpl {

    private static final CheckUtilsImpl instance = new CheckUtilsImpl() {};

    private CheckUtilsImpl() {}

    /**
     * 获取单实例
     * 
     * @return
     */
    public static CheckUtilsImpl getInstance() {
        return instance;
    }

    /**
     * <p>
     * 判断是否为空格、""、null
     * </p>
     * 
     * <pre>
     * isBlank(null)      = true 
     * isBlank("")        = true
     * isBlank(" ")       = true
     * isBlank("bob")     = false
     * isBlank("  bob  ") = false
     * </pre>
     * 
     * @param cs 字符序列
     * @return 是否为空
     */
    public boolean isBlank(CharSequence cs) {
        return CoreUtils.isBlank(cs);
    }

    /**
     * <p>
     * 判断给定对象是否为空,支持以下数据类型.
     * </p>
     * <ul>
     * <li>{@code Array}: 数组的长度为0</li>
     * <li>{@link CharSequence}: 字符串的长度为0</li>
     * <li>{@link Collection}: 集合对象是否为空 {@link Collection#isEmpty()}</li>
     * <li>{@link Map}: Map对象是否为空{@link Map#isEmpty()}</li>
     * </ul>
     * <p>
     * 如果给定对象为空或不在支持的对象类型之内则返回{@code false}.
     * </p>
     * 
     * @param obj 判断对象
     * @return 是否为空
     */
    public boolean isEmpty(Object obj) {
        return CoreUtils.isEmpty(obj);
    }

    /**
     * 是否字母
     * 
     * @param cs 字符序列
     * @return 是否为字母
     */
    public boolean isAlpha(final CharSequence cs) {
        return StringUtils.isAlpha(cs);
    }

    /**
     * 是否字母数字
     * 
     * @param cs 字符序列
     * @return 是否为字母或数字
     */
    public boolean isAlphanumeric(final CharSequence cs) {
        return StringUtils.isAlphanumeric(cs);
    }

    /**
     * 是否数字
     * 
     * @param cs 字符序列
     * @return 是否为数字
     */
    public boolean isNumeric(final CharSequence cs) {
        return StringUtils.isNumeric(cs);
    }

    /**
     * 是否为真
     * 
     * @param bean 目标对象
     * @param expression 目标表达式
     * @return 是否为true
     */
    public boolean isTrue(Object bean, String expression) {
        return isTrue(bean, expression, false);
    }

    /**
     * 是否为真
     * 
     * @param bean 目标对象
     * @param expression 目标表达式
     * @param valueIfNull 如果对象或表达式为null时的默认值
     * @return 是否为true，内部调用Tool.Bean.getProperty实现
     */
    public boolean isTrue(Object bean, String expression, boolean valueIfNull) {
        try {
            if (null == bean || isBlank(expression)) {
                return valueIfNull;
            }
            return Tool.BEAN.getProperty(bean, expression, null, boolean.class);
        } catch (Exception e) {
            return valueIfNull;
        }
    }

    /**
     * 是否为真
     * 
     * @param bean 目标对象
     * @param expression 目标表达式
     * @param context 上下文环境
     * @param valueIfNull 如果对象或表达式为null时的默认值
     * @return 是否为true，内部调用Tool.Bean.getProperty实现
     */
    public boolean isTrue(Object bean, String expression, Map<String, Object> context, boolean valueIfNull) {
        try {
            if (null == bean || isBlank(expression)) {
                return valueIfNull;
            }
            return Tool.BEAN.getProperty(bean, expression, context, boolean.class);
        } catch (Exception e) {
            return valueIfNull;
        }
    }

    /**
     * 是否有效日期
     * 
     * @param date 字符串日期
     * @param format 目前支持YYYYMMDD、YYYY、YYYYMM、YYYY-MM-DD、YYYY/MM/DD这些格式
     * @return 是否有效日期
     */
    public boolean isDate(String date, String format) {
        return Tool.DATE.isValidDate(date, format);
    }

    /**
     * 是否有效日期
     * 
     * @param year 年份
     * @param month 月份
     * @param day 天数
     * @return 是否有效日期
     */
    public boolean isDate(int year, int month, int day) {
        return Tool.DATE.isValidDate(year, month, day);
    }

    /**
     * 是否有效时间
     * 
     * @param hour 小时数
     * @param minute 分钟数
     * @param second 秒数
     * @param millisecond 毫秒数
     * @return 是否有效时间
     */
    public boolean isTime(int hour, int minute, int second, int millisecond) {
        return Tool.DATE.isValidTime(hour, minute, second, millisecond);
    }
}
