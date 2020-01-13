package com.forms.beneform4j.core.dao.sql.interceptor.impl;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.forms.beneform4j.core.Beneform4jConfig;
import com.forms.beneform4j.core.dao.dialect.IDialect;
import com.forms.beneform4j.core.dao.jndi.IJndi;
import com.forms.beneform4j.core.dao.sql.resolver.ISqlResolver;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 使用属性文件替换解析被拦截的表达式<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-6<br>
 */
public class BaseSqlInterceptor extends AbstractSqlInterceptor {

    private final Properties properties;

    private Map<IDialect, Properties> dialectProperties;

    public BaseSqlInterceptor() {
        properties = new Properties();
        properties.put("tablePrefix", Beneform4jConfig.getBeneform4jTablePrefix());
        properties.put("BF", Beneform4jConfig.getBeneform4jTablePrefix() + "BF");
        super.setPrefix("{{");
        super.setSuffix("}}");
    }

    @Override
    protected String doIntercept(IJndi jndi, String expression, Object root) {
        try {
            Properties properties = getProperties();
            if (properties.containsKey(expression)) {
                return properties.getProperty(expression);
            } else {
                Properties dialectProperties = obtainDialectProperties(jndi);
                if (null != dialectProperties && dialectProperties.containsKey(expression)) {
                    return dialectProperties.getProperty(expression);
                } else {
                    ISqlResolver spr = getSqlResolver(jndi, expression);
                    if (null != spr) {
                        return spr.resolverSql(jndi, root, expression);
                    } else {
                        return expression;
                    }
                }
            }
        } catch (Exception e) {
            return expression;
        }
    }

    private ISqlResolver getSqlResolver(IJndi jndi, String propertyName) {
        List<? extends ISqlResolver> sprs = Beneform4jConfig.getSqlResolvers();
        if (null != sprs && !sprs.isEmpty()) {
            for (ISqlResolver spr : sprs) {
                if (spr.isSupport(jndi, propertyName)) {
                    return spr;
                }
            }
        }
        return null;
    }

    private Properties obtainDialectProperties(IJndi jndi) {
        if (null != dialectProperties) {
            return dialectProperties.get(jndi.getDialect());
        }
        return null;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        if (null != properties) {
            this.properties.putAll(properties);
        }
    }

    public Map<IDialect, Properties> getDialectProperties() {
        return dialectProperties;
    }

    public void setDialectProperties(Map<IDialect, Properties> dialectProperties) {
        this.dialectProperties = dialectProperties;
    }
}
