package com.forms.beneform4j.util.toolimpl;

import java.util.Map;

import com.forms.beneform4j.core.util.CoreUtils;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : Bean工具类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-2-23<br>
 */
public abstract class BeanUtilsImpl {

    private static final BeanUtilsImpl instance = new BeanUtilsImpl() {};

    private BeanUtilsImpl() {}

    /**
     * 获取单实例
     * 
     * @return
     */
    public static BeanUtilsImpl getInstance() {
        return instance;
    }

    /**
     * 获取属性
     * 
     * @param bean 目标对象
     * @param property 属性表达式
     * @return 属性值
     */
    public Object getProperty(Object bean, String property) {
        return CoreUtils.getProperty(bean, property);
    }

    /**
     * 获取属性，并按照期望的结果类型转换
     * 
     * @param bean 目标对象
     * @param property 属性表达式
     * @param resultType 期望的结果类型
     * @return 属性值
     */
    public <E> E getProperty(Object bean, String property, Class<E> resultType) {
        return CoreUtils.getProperty(bean, property, resultType);
    }

    /**
     * 设置属性
     * 
     * @param bean 目标对象
     * @param property 属性表达式
     * @param value 属性值
     */
    public void setProperty(Object bean, String property, Object value) {
        CoreUtils.setProperty(bean, property, value);
    }

    /**
     * 移除属性
     * 
     * @param bean 目标对象
     * @param property 属性表达式
     */
    public void removeProperty(Object bean, String property) {
        CoreUtils.removeProperty(bean, property);
    }

    /**
     * 获取上下文环境中的表达式值
     * 
     * @param bean 目标对象
     * @param expression 属性表达式
     * @param context 上下文环境，Map结构
     * @return 表达式值
     */
    public Object getProperty(Object bean, String expression, Map<String, Object> context) {
        return CoreUtils.getProperty(bean, expression, context);
    }

    /**
     * 获取上下文环境中的表达式值，并按照期望的结果类型进行类型转换
     * 
     * @param bean 目标对象
     * @param expression 属性表达式
     * @param context 上下文环境，Map结构
     * @param resultType 期望的结果类型
     * @return 表达式值
     */
    public <E> E getProperty(Object bean, String expression, Map<String, Object> context, Class<E> resultType) {
        return CoreUtils.getProperty(bean, expression, context, resultType);
    }

    /**
     * 设置上下文环境中的表达式值
     * 
     * @param bean 目标对象
     * @param expression 属性表达式
     * @param value 表达式值
     * @param context 上下文环境，Map结构
     */
    public void setProperty(Object bean, String expression, Object value, Map<String, Object> context) {
        CoreUtils.setProperty(bean, expression, value, context);
    }

    /**
     * 获取一组表达式的值
     * 
     * @param bean 目标对象
     * @param expressions 一组表达式
     * @return 一组表达式的值
     */
    public Object[] getProperties(Object bean, String[] expressions) {
        return CoreUtils.getProperties(bean, expressions);
    }

    /**
     * 获取一组表达式的值
     * 
     * @param bean 目标对象
     * @param expressions 一组表达式
     * @param context 上下文环境，Map结构
     * @return 一组表达式的值
     */
    public Object[] getProperties(Object bean, String[] expressions, Map<String, Object> context) {
        return CoreUtils.getProperties(bean, expressions, context);
    }
}
