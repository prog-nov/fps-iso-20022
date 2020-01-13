package com.forms.beneform4j.core.dao.sql.function.impl;

import com.forms.beneform4j.core.dao.jndi.IJndi;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 抽象的使用解析值作为解析SQL的配置函数实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-10-26<br>
 */
public abstract class AbstractValueSqlConfigFunction extends AbstractSqlConfigFunction {

    @Override
    public String evaluateSql(IJndi jndi, Object parameter, String expression, String[] args) {
        Object value = this.evaluateValue(jndi, parameter, expression, args);
        if (value instanceof Number) {
            return value.toString();
        } else {
            return "'" + value.toString() + "'";
        }
    }

}
