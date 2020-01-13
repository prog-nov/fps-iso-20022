package com.forms.beneform4j.core.dao.sql.function.impl;

import com.forms.beneform4j.core.dao.sql.function.ISqlConfigFunction;
import com.forms.beneform4j.core.dao.sql.function.ISqlConfigFunctionFactory;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 抽象的返回单实例SQL配置函数工厂实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-10-26<br>
 */
public abstract class AbstractSingleonSqlConfigFunctionFactory implements ISqlConfigFunctionFactory {

    @Override
    public ISqlConfigFunction getSqlConfigFunction(String name) {
        return null;
    }

}
