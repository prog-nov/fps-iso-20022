package com.forms.beneform4j.core.dao.mybatis.provider;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.DatabaseIdProvider;

import com.forms.beneform4j.core.dao.dialect.IDialect;
import com.forms.beneform4j.core.dao.jndi.JndiManager;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 使用数据库方言实现的数据库提供商接口实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24<br>
 */
public class DialectDatabaseIdProvider implements DatabaseIdProvider {

    /**
     * 获取数据库产品的简写名称
     * <p>
     * 首先根据数据源获取数据库方言，然后获取相应的数据库产品
     * </p>
     */
    @Override
    public String getDatabaseId(DataSource dataSource) throws SQLException {
        IDialect dialect = JndiManager.getDialect(dataSource);
        return dialect.getType().name().toLowerCase();
    }

    @Override
    public void setProperties(Properties p) {}
}
