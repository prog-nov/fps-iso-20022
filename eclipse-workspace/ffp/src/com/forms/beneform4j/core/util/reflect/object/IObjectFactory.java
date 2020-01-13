package com.forms.beneform4j.core.util.reflect.object;

import java.util.List;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 对象工厂接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-27<br>
 */
public interface IObjectFactory {

    /**
     * 创建实例
     * 
     * @param type 对象类型
     * @param constructorArgs 构造器参数值
     * @return 实例
     */
    <T> T create(Class<T> type, Object... constructorArgs);

    /**
     * 创建实例
     * 
     * @param type 对象类型
     * @param constructorArgTypes 构造器参数类型
     * @param constructorArgs 构造器参数值
     * @return 实例
     */
    <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, Object... constructorArgs);

    /**
     * 创建实例
     * 
     * @param className 对象类型名称
     * @param constructorArgs 构造器参数值
     * @return 实例
     */
    Object create(String className, Object... constructorArgs);

    /**
     * 创建实例
     * 
     * @param className 对象类型名称
     * @param constructorArgTypes 构造器参数类型
     * @param constructorArgs 构造器参数值
     * @return 实例
     */
    Object create(String className, List<Class<?>> constructorArgTypes, Object... constructorArgs);
}
