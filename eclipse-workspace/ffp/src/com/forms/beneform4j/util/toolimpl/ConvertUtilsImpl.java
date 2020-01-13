package com.forms.beneform4j.util.toolimpl;

import java.util.List;

import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.config.BaseConfig;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 类型转换工具类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-2-23<br>
 */
public abstract class ConvertUtilsImpl {

    private static final ConvertUtilsImpl instance = new ConvertUtilsImpl() {};

    private ConvertUtilsImpl() {}

    /**
     * 获取单实例
     * 
     * @return
     */
    public static ConvertUtilsImpl getInstance() {
        return instance;
    }

    /**
     * 字符串转换为布尔类型
     * 
     * @param str 字符串 1、Y、TRUE、ON（忽略大小写）返回true，否则返回false
     * @return 布尔值
     */
    public boolean string2Boolean(String str) {
        return CoreUtils.string2Boolean(str);
    }

    /**
     * 强制类型转换，直接转换类型，若类型不匹配，抛出异常
     * 
     * @param obj 对象
     * @return 转换后的对象
     */
    public <T> T cast(Object obj) {
        return CoreUtils.cast(obj);
    }

    /**
     * 源类型到目标类型的转换，使用Spring中的类型转换接口ConfigurableConversionService实现
     * 
     * @param source 对象
     * @param targetType 目标类型
     * @return 转换后的对象
     */
    public <T> T convert(Object source, Class<T> targetType) {
        return BaseConfig.getConversionService().convert(source, targetType);
    }

    /**
     * 转换为List类型，如果不为空，只校验第一个元素的类型是否匹配
     * 
     * @param source 源对象
     * @param elementType 元素类型
     * @return 转换后的集合
     */
    public <E> List<E> convertToList(Object source, Class<E> elementType) {
        return CoreUtils.convertToList(source, elementType);
    }

    /**
     * Number类型转换，将Number类型转换为子类型
     * 
     * @param number 源对象
     * @param targetClass 目标对象类型
     * @return 转换后的子类型对象
     */
    public <T extends Number> T convertNumberToTargetClass(Number number, Class<T> targetClass) {
        return CoreUtils.convertNumberToTargetClass(number, targetClass);
    }

    /**
     * 字符串解析为Number类型
     * 
     * @param text 源字符串
     * @param targetClass 目标对象类型
     * @return 转换后的Number子类型对象
     */
    public <T extends Number> T convertStringToTargetClass(String text, Class<T> targetClass) {
        return CoreUtils.convertStringToTargetClass(text, targetClass);
    }

    /**
     * 转换IP地址，127.0.0.1、0:0:0:0:0:0:0:1、0.0.0.0转换为Inet4Address.getLocalHost().getHostAddress()，其它不变
     * 
     * @param ip 原始IP地址
     * @return 转换后的IP地址
     */
    public String convertIp(String ip) {
        return CoreUtils.convertIp(ip);
    }
}
