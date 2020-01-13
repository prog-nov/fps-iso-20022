package com.forms.beneform4j.core.service.spring.component;

import java.util.Map;

import com.forms.beneform4j.core.service.spring.SpringHelp;
import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.bean.impl.BaseBeanOperateWrapper;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 混合了SpEL表达式的bean对象操作封装类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24<br>
 */
public class SpringContextBeanOperateWrapper extends BaseBeanOperateWrapper {

    private String prefix = "spel:";

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * property为Spel表达式时（前缀相同，或者对象为beanId），获取Spel表达式，如果不是Spel表达式，返回null
     * 
     * @param bean 被操作对象
     * @param property 属性/表达式
     * @return 表达式值
     */
    protected String getSpelExpression(Object bean, String property) {
        if (CoreUtils.isBlank(property) || CoreUtils.isBlank(getPrefix())) {
            return null;
        } else if (property.startsWith(getPrefix())) {
            return property.substring(getPrefix().length());
        } else if (bean instanceof String && SpringHelp.containsBean((String) bean)) {
            return property;
        } else {
            return null;
        }
    }

    /**
     * 获取属性值
     */
    @Override
    public Object getProperty(Object bean, String property) {
        String spel = getSpelExpression(bean, property);
        if (!CoreUtils.isBlank(spel)) {
            return SpringHelp.evaluate(bean, spel);
        }
        return super.getProperty(bean, property);
    }

    /**
     * 按期望的类型获取属性值
     */
    @Override
    public <E> E getProperty(Object bean, String property, Class<E> resultType) {
        String spel = getSpelExpression(bean, property);
        if (!CoreUtils.isBlank(spel)) {
            return SpringHelp.evaluate(bean, spel, null, resultType);
        }
        return super.getProperty(bean, property, resultType);
    }

    /**
     * 设置属性值
     */
    @Override
    public void setProperty(Object bean, String property, Object value) {
        String spel = getSpelExpression(bean, property);
        if (!CoreUtils.isBlank(spel)) {
            SpringHelp.setValue(bean, spel, null, value);
        }
        super.setProperty(bean, property, value);
    }

    /**
     * 移除属性值
     */
    @Override
    public void removeProperty(Object bean, String property) {
        String spel = getSpelExpression(bean, property);
        if (!CoreUtils.isBlank(spel)) {
            SpringHelp.setValue(bean, spel, null, null);
        }
        super.removeProperty(bean, property);
    }

    /**
     * 获取上下文中的表达式值
     */
    @Override
    public Object getProperty(Object bean, String expression, Map<String, Object> context) {
        String spel = getSpelExpression(bean, expression);
        if (!CoreUtils.isBlank(spel)) {
            return SpringHelp.evaluate(bean, spel, context);
        }
        return super.getProperty(bean, expression, context);
    }

    /**
     * 按期望的类型获取上下文中的表达式值
     */
    @Override
    public <E> E getProperty(Object bean, String expression, Map<String, Object> context, Class<E> resultType) {
        String spel = getSpelExpression(bean, expression);
        if (!CoreUtils.isBlank(spel)) {
            return SpringHelp.evaluate(bean, spel, context, resultType);
        }
        return super.getProperty(bean, expression, context, resultType);
    }

    /**
     * 设置上下文中的表达式值
     */
    @Override
    public void setProperty(Object bean, String expression, Object value, Map<String, Object> context) {
        String spel = getSpelExpression(bean, expression);
        if (!CoreUtils.isBlank(spel)) {
            SpringHelp.setValue(bean, spel, context, value);
        }
        super.setProperty(bean, expression, value, context);
    }
}
