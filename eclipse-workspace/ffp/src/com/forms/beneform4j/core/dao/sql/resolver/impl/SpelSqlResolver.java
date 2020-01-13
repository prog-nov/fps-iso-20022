package com.forms.beneform4j.core.dao.sql.resolver.impl;

import com.forms.beneform4j.core.dao.jndi.IJndi;
import com.forms.beneform4j.core.util.spring.SpEL;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 使用Spel解析SQL的实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24<br>
 */
public class SpelSqlResolver extends AbstractSqlResolver {

    /**
     * 参数表达式以@开头作为Spel表达式的标志
     */
    @Override
    public boolean isSupport(IJndi jndi, String expression) {
        return null != expression && expression.startsWith("@");
    }

    /**
     * 使用SpEL表达式解析值
     */
    @Override
    public Object resolver(IJndi jndi, Object parameterObject, String expression) {
        String exp = expression.substring(1);
        //return SpringHelp.evaluate(parameterObject, exp);
        return SpEL.getValue(parameterObject, exp);
    }

}
