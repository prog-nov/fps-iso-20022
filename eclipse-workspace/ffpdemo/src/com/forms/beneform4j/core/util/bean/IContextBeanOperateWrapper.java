package com.forms.beneform4j.core.util.bean;

import java.util.Map;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 上下文中的Bean操作接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-22<br>
 */
public interface IContextBeanOperateWrapper extends IBeanOperateWrapper {

    /**
     * 获取上下文中的表达式值
     * 
     * @param bean bean对象
     * @param expression 属性表达式
     * @param context 上下文环境
     * @return 表达式值
     */
    public Object getProperty(Object bean, String expression, Map<String, Object> context);

    /**
     * 获取上下文中的表达式值，并根据结果类型转换
     * 
     * @param bean bean对象
     * @param expression 属性表达式
     * @param context 上下文环境
     * @param resultType 期望的目录类型
     * @return 表达式值
     */
    public <E> E getProperty(Object bean, String expression, Map<String, Object> context, Class<E> resultType);

    /**
     * 设置上下文中的表达式值
     * 
     * @param bean bean对象
     * @param expression 属性表达式
     * @param value 表达式值
     * @param context 上下文环境
     */
    public void setProperty(Object bean, String expression, Object value, Map<String, Object> context);
}
