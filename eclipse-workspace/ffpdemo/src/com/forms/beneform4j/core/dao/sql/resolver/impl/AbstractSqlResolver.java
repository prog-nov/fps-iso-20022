package com.forms.beneform4j.core.dao.sql.resolver.impl;

import com.forms.beneform4j.core.dao.jndi.IJndi;
import com.forms.beneform4j.core.dao.sql.resolver.ISqlResolver;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 抽象的SQL解析器，SQL语句使用解析值替换<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-10-26<br>
 */
public abstract class AbstractSqlResolver implements ISqlResolver {

    @Override
    public String resolverSql(IJndi jndi, Object parameterObject, String expression) {
        Object value = this.resolver(jndi, parameterObject, expression);
        return null == value ? expression : value.toString();
    }
}
