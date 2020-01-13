package com.forms.beneform4j.core.dao.sql.function;

import java.util.Set;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : SQL配置函数工厂<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-10-26<br>
 */
public interface ISqlConfigFunctionFactory {

    /**
     * 获取所有配置函数
     * 
     * @return
     */
    public Set<ISqlConfigFunction> getAllSqlConfigFunctions();

    /**
     * 根据函数名返回配置函数，一般只有非单例的配置函数才需要实现此方法
     * 
     * @param name 函数名
     * @return 配置函数
     */
    public ISqlConfigFunction getSqlConfigFunction(String name);
}
