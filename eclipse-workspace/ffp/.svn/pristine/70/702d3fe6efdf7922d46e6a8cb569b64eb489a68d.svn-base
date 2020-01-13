package com.forms.beneform4j.core.util.reflect.object;

import java.util.List;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 对象混入接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.1 <br>
 * Since : 1.0.1 <br>
 * Date : 2016-1-17<br>
 */
public interface IObjectMixin<T> {

    /**
     * 获取混入实例
     * 
     * @param constructorArgs 构造器参数
     * @return 混入实例
     */
    public T getInstance(Object... constructorArgs);

    /**
     * 获取混入实例
     * 
     * @param constructorArgTypes 构造器参数类型
     * @param constructorArgs 构造器参数
     * @return 混入实例
     */
    public T getInstance(List<Class<?>> constructorArgTypes, Object... constructorArgs);
}
