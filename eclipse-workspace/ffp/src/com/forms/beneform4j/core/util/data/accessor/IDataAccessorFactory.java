package com.forms.beneform4j.core.util.data.accessor;

import java.util.Map;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 数据访问器工厂<br>
 * Author : LinJisong <br>
 * Version : 1.0.1 <br>
 * Since : 1.0.1 <br>
 * Date : 2017-1-17<br>
 */
public interface IDataAccessorFactory {

    /**
     * 创建新的数据访问器对象
     * 
     * @return 数据访问器对象
     */
    public IDataAccessor newDataAccessor();

    /**
     * 根据root对象创建新的数据访问器对象
     * 
     * @param root root对象
     * @return 数据访问器对象
     */
    public IDataAccessor newDataAccessor(Object root);

    /**
     * 根据root对象和变量创建新的数据访问器对象
     * 
     * @param root root对象
     * @param vars 变量
     * @return 数据访问器对象
     */
    public IDataAccessor newDataAccessor(Object root, Map<String, Object> vars);
}
