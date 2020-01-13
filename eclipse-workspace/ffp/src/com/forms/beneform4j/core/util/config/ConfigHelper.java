package com.forms.beneform4j.core.util.config;

import java.util.Arrays;
import java.util.List;

import com.forms.beneform4j.core.util.CoreUtils;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 配置帮助类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-22<br>
 */
public class ConfigHelper {

    /**
     * 视作为空的整型值
     */
    public static final int EQUAL_EMPTY_INT_VALUE = Integer.MIN_VALUE;

    /**
     * 获取整型值，如果值为空，返回默认配置名称相对应的值
     * 
     * @param value 用户配置值
     * @param defaultConfigName 默认配置名称
     * @return 配置值
     */
    public static int getValue(int value, String defaultConfigName) {
        if (EQUAL_EMPTY_INT_VALUE == value) {
            return Defaults.getDefaultConfig(defaultConfigName, int.class);
        }
        return value;
    }

    /**
     * 获取字符串值，如果值为空，返回默认配置名称相对应的值
     * 
     * @param value 用户配置值
     * @param defaultConfigName 默认配置名称
     * @return 配置值
     */
    public static String getValue(String value, String defaultConfigName) {
        if (CoreUtils.isBlank(value)) {
            return Defaults.getDefaultConfig(defaultConfigName);
        }
        return value;
    }

    /**
     * 获取一组值组，如果值组为空，返回默认配置名称相对应的值组
     * 
     * @param values 用户配置值
     * @param defaultConfigName 默认配置名称
     * @return 配置值
     */
    public static List<String> getValues(List<String> values, String defaultConfigName) {
        if (null == values || values.isEmpty()) {
            String[] s = Defaults.getDefaultConfigs(defaultConfigName);
            if (null != s && s.length >= 1) {
                return Arrays.asList(s);
            }
        }
        return values;
    }

    /**
     * 合并值组，如果值组为空，返回默认配置名称相对应的值组，否则在原基础上添加默认的配置
     * 
     * @param values 用户配置值
     * @param defaultConfigName 默认配置名称
     * @return 配置值
     */
    public static List<String> combineValues(List<String> values, String defaultConfigName) {
        String[] s = Defaults.getDefaultConfigs(defaultConfigName);
        if (null != s && s.length >= 1) {
            if (null == values || values.isEmpty()) {
                return Arrays.asList(s);
            } else {
                values.addAll(Arrays.asList(s));
            }
        }
        return values;
    }

    /**
     * 获取组件，如果组件为空，将组件类型作为key值查找对应的默认组件
     * 
     * @param component 用户配置组件
     * @param cls 组件类型
     * @return 配置组件
     */
    public static <E> E getComponent(E component, Class<E> cls) {
        if (null == component) {
            return Defaults.getDefaultComponent(cls);
        }
        return component;
    }

    /**
     * 获取组件组，如果组进组为空，将组件类型作为key值查找对应的默认组件组
     * 
     * @param components 用户配置组件组
     * @param cls 组件类型
     * @return 配置组件组
     */
    public static <E> List<E> getComponents(List<E> components, Class<E> cls) {
        if (null == components || components.isEmpty()) {
            E[] d = Defaults.getDefaultComponents(cls);
            if (null != d && d.length > 0) {
                return Arrays.asList(d);
            }
        }
        return components;
    }

    /**
     * 合并组件组，如果组件组为空，返回组件类型作为key值对应的默认组件组，否则在原基础上添加默认的配置组件
     * 
     * @param components 用户配置组件组
     * @param cls 组件类型
     * @return 配置组件组
     */
    public static <E> List<E> combineComponents(List<E> components, Class<E> cls) {
        E[] d = Defaults.getDefaultComponents(cls);
        if (null != d && d.length > 0) {
            if (null == components || components.isEmpty()) {
                return Arrays.asList(d);
            } else {
                components.addAll(Arrays.asList(d));
            }
        }
        return components;
    }

    /**
     * 获取组件，如果组件为空，将组件类型加上井号#，再加上名称作为key值查找对应的默认组件
     * 
     * @param component 用户配置组件
     * @param cls 组件类型
     * @param name 组件名称
     * @return 配置组件
     */
    public static <E> E getComponent(E component, Class<E> cls, String name) {
        if (null == component) {
            return Defaults.getDefaultComponent(cls, name);
        }
        return component;
    }

    /**
     * 获取组件组，如果组件组为空，将组件类型加上井号#，再加上名称作为key值查找对应的默认组件
     * 
     * @param components 用户配置组件组
     * @param cls 组件类型
     * @param name 组件名称
     * @return 配置组件组
     */
    public static <E> List<E> getComponents(List<E> components, Class<E> cls, String name) {
        if (null == components || components.isEmpty()) {
            E[] d = Defaults.getDefaultComponents(cls, name);
            if (null != d && d.length > 0) {
                return Arrays.asList(d);
            }
        }
        return components;
    }

    /**
     * 合并组件组，如果组件组为空，将组件类型加上井号#，再加上名称作为key值查找对应的默认组件，否则在原基础上添加默认的配置组件
     * 
     * @param components 用户配置组件组
     * @param cls 组件类型
     * @param name 组件名称
     * @return 配置组件组
     */
    public static <E> List<E> combineComponents(List<E> components, Class<E> cls, String name) {
        E[] d = Defaults.getDefaultComponents(cls, name);
        if (null != d && d.length > 0) {
            if (null == components || components.isEmpty()) {
                return Arrays.asList(d);
            } else {
                components.addAll(Arrays.asList(d));
            }
        }
        return components;
    }
}
