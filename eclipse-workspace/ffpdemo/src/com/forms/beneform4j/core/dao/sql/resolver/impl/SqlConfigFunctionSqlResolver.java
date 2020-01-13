package com.forms.beneform4j.core.dao.sql.resolver.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.forms.beneform4j.core.dao.jndi.IJndi;
import com.forms.beneform4j.core.dao.sql.function.ISqlConfigFunction;
import com.forms.beneform4j.core.dao.sql.resolver.ISqlResolver;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 使用自定义函数解析SQL的实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-10-26<br>
 */
public class SqlConfigFunctionSqlResolver implements ISqlResolver {

    private static final Pattern pattern = Pattern.compile("^\\s*(\\w+)\\s*\\(\\s*(.*)\\s*\\)\\s*$");

    /**
     * 如果符合函数调用模式，视作自定义函数
     */
    @Override
    public boolean isSupport(IJndi jndi, String expression) {
        return null != expression && pattern.matcher(expression).find();
    }

    /**
     * 自定义函数表达式解析值
     */
    @Override
    public Object resolver(IJndi jndi, Object parameterObject, String expression) {
        Matcher matcher = pattern.matcher(expression);
        if (matcher.find()) {
            String fname = matcher.group(1);
            String args = matcher.group(2);
            ISqlConfigFunction fn = ISqlConfigFunction.Manager.getSqlTextFunction(fname);
            if (null != fn) {
                return fn.evaluateValue(jndi, parameterObject, args, args == null ? null : args.split("\\s+|(\\s*,\\s*)"));
            }
        }
        return expression;
    }

    @Override
    public String resolverSql(IJndi jndi, Object parameterObject, String expression) {
        Matcher matcher = pattern.matcher(expression);
        if (matcher.find()) {
            String fname = matcher.group(1);
            String args = matcher.group(2);
            ISqlConfigFunction fn = ISqlConfigFunction.Manager.getSqlTextFunction(fname);
            if (null != fn) {
                return fn.evaluateSql(jndi, parameterObject, args, args == null ? null : args.split("\\s+|(\\s*,\\s*)"));
            }
        }
        return expression;
    }
}
