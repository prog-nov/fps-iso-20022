package com.forms.beneform4j.core.dao.sql;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import com.forms.beneform4j.core.Beneform4jConfig;
import com.forms.beneform4j.core.dao.annotation.SqlRef;
import com.forms.beneform4j.core.dao.jndi.IJndi;
import com.forms.beneform4j.core.dao.sql.interceptor.ISqlInterceptor;
import com.forms.beneform4j.core.dao.sql.mapper.ISqlMapperStrategy;
import com.forms.beneform4j.core.util.CoreUtils;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : Sql方法管理类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24<br>
 */
public class SqlManager {

    /**
     * 获取真正需要执行的sqlId，实现SQL-ID的整体替换
     * 
     * @param sqlId 原SQL-ID
     * @return 需要实际执行的SQL-ID
     */
    public static String getExecuteSqlId(String sqlId) {
        Map<String, String> sqlIdMapping = Beneform4jConfig.getSqlIdMapping();
        if (null != sqlIdMapping && null != sqlId && null != sqlIdMapping.get(sqlId)) {
            return sqlIdMapping.get(sqlId);
        } else {
            return sqlId;
        }
    }

    /**
     * 获取和方法对应的SQL-ID
     * 
     * @param method 方法
     * @return 需要执行的SQL-ID
     */
    public static String getSqlId(Method method) {
        ISqlMapperStrategy sqlMapperStrategy = Beneform4jConfig.getSqlMapperStrategy();
        if (null != sqlMapperStrategy) {
            return sqlMapperStrategy.lookup(method);
        }
        return null;
    }

    /**
     * 将@SqlRef注解解析为sqlId
     * 
     * @param sqlRef 注解
     * @param method 方法
     * @return 需要实际执行的SQL-ID
     */
    public static String resolverSqlId(SqlRef sqlRef, Method method) {
        String rs = sqlRef.value();
        if (CoreUtils.isBlank(rs)) {
            rs = method.getName();
        }

        if (sqlRef.classpath()) {
            rs = method.getDeclaringClass().getName() + "." + rs;
        }
        return rs;
    }

    /**
     * 执行SQL拦截
     * 
     * @param jndi 数据源
     * @param src 原SQL
     * @param root 根对象
     * @return 拦截后的SQL
     */
    public static String doIntercept(IJndi jndi, String src, Object root) {
        List<? extends ISqlInterceptor> is = Beneform4jConfig.getSqlInterceptors();
        if (null != is && !is.isEmpty()) {
            for (ISqlInterceptor s : is) {
                src = s.intercept(jndi, src, root);
            }
        }
        return src;
    }
}
