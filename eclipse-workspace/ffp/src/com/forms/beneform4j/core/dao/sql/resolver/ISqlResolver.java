package com.forms.beneform4j.core.dao.sql.resolver;

import com.forms.beneform4j.core.dao.jndi.IJndi;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : SQL解析器接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24<br>
 */
public interface ISqlResolver {

    /**
     * 是否可以解析表达式
     * 
     * @param jndi 数据源对象
     * @param expression 表达式
     * @return 如果可以解析，返回true，否则返回false
     */
    public boolean isSupport(IJndi jndi, String expression);

    /**
     * 在设置参数值的时候，解析为值，用于JDBC中的PreparedStatement.setObject()/setString()/setDouble()...等方法
     * 
     * @param jndi 数据源对象
     * @param parameterObject 参数对象
     * @param expression 表达式
     * @return 解析后的值
     */
    public Object resolver(IJndi jndi, Object parameterObject, String expression);

    /**
     * 在替换SQL语句的时候，解析为SQL字符串
     * 
     * @param jndi 数据源对象
     * @param parameterObject 参数对象
     * @param expression 表达式
     * @return 需要替换的SQL字符串
     */
    public String resolverSql(IJndi jndi, Object parameterObject, String expression);
}
