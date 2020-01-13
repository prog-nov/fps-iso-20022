package com.forms.beneform4j.core.dao.sql.resolver.impl;

import org.apache.ibatis.ognl.Ognl;
import org.apache.ibatis.ognl.OgnlException;

import com.forms.beneform4j.core.dao.exception.DaoExceptionCodes;
import com.forms.beneform4j.core.dao.jndi.IJndi;
import com.forms.beneform4j.core.util.exception.Throw;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 使用Ognl解析SQL的实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24<br>
 */
public class OgnlSqlResolver extends AbstractSqlResolver {

    /**
     * 参数表达式以%开头作为OGNL表达式的标志
     */
    @Override
    public boolean isSupport(IJndi jndi, String expression) {
        return null != expression && expression.startsWith("%");
    }

    /**
     * 使用OGNL表达式解析值
     */
    @Override
    public Object resolver(IJndi jndi, Object parameterObject, String expression) {
        String exp = expression.substring(1);
        try {
            return Ognl.getValue(exp, parameterObject);
        } catch (OgnlException e) {
            throw Throw.createRuntimeException(DaoExceptionCodes.BF020008, e, exp);
        }
    }

}
