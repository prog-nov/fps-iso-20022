package com.forms.beneform4j.core.dao.sql.function.impl;

import org.apache.ibatis.ognl.Ognl;
import org.apache.ibatis.ognl.OgnlException;

import com.forms.beneform4j.core.dao.dialect.IDialect.DBType;
import com.forms.beneform4j.core.dao.jndi.IJndi;
import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.exception.Throw;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : SQL配置函数（字符串连接）<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-10-26<br>
 */
public class ConcatSqlConfigFunction extends AbstractSqlConfigFunction {

    @Override
    public String getName() {
        return "concat";
    }

    @Override
    public Object evaluateValue(IJndi jndi, Object parameter, String expression, String[] args) {
        try {
            return Ognl.getValue(CoreUtils.join(args, "+"), parameter);
        } catch (OgnlException e) {
            e.printStackTrace();
            throw Throw.createRuntimeException(e);
        }
    }

    @Override
    public String evaluateSql(IJndi jndi, Object parameter, String expression, String[] args) {
        DBType type = jndi.getDialect().getType();
        if (DBType.MySql.equals(type)) {
            return "CONCAT(" + CoreUtils.join(args, ",") + ")";
        } else {
            return CoreUtils.join(args, "||");
        }
    }
}
