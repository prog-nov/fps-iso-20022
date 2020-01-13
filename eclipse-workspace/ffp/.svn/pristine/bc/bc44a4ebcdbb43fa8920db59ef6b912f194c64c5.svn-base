package com.forms.beneform4j.core.util.bean;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : Bean操作接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-22<br>
 */
public interface IBeanOperateWrapper {

    /**
     * 获取bean属性
     * 
     * @param bean bean对象
     * @param property 属性名称，可以是嵌套属性
     * @return 属性值
     */
    public Object getProperty(Object bean, String property);

    /**
     * 获取bean属性，并根据返回类型转换
     * 
     * @param bean bean对象
     * @param property 属性名称，可以是嵌套属性
     * @param resultType 期望的目标类型
     * @return 属性值
     */
    public <E> E getProperty(Object bean, String property, Class<E> resultType);

    /**
     * 设置bean属性
     * 
     * @param bean bean对象
     * @param property 属性名称，可以是嵌套属性
     * @param value 属性值
     */
    public void setProperty(Object bean, String property, Object value);

    /**
     * 移除bean属性
     * 
     * @param bean bean对象
     * @param property 属性名称，可以是嵌套属性，嵌套属性只移除最后一层级的属性值
     */
    public void removeProperty(Object bean, String property);
}
