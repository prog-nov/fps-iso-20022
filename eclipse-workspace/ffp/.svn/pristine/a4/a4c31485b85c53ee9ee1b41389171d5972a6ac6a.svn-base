package com.forms.beneform4j.core.dao.sql.mapper.impl;

import java.lang.reflect.Method;

import com.forms.beneform4j.core.dao.annotation.SqlRef;
import com.forms.beneform4j.core.dao.sql.SqlManager;
import com.forms.beneform4j.core.dao.sql.mapper.ISqlMapperStrategy;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 根据@SqlRef和类名称获取对应SQL-ID<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24<br>
 */
public class SqlRefClassNameMapperStrategy implements ISqlMapperStrategy {

    /**
     * 查找对应的SQL-ID
     */
    public String lookup(Method method) {
        String rs = "";
        SqlRef sqlRef = method.getAnnotation(SqlRef.class);
        if (null != sqlRef) {
            rs = SqlManager.resolverSqlId(sqlRef, method);
        } else {
            rs = method.getDeclaringClass().getName() + "." + method.getName();
        }
        return SqlManager.getExecuteSqlId(rs);
    }

}
