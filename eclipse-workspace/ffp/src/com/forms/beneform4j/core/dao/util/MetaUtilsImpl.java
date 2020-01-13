package com.forms.beneform4j.core.dao.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.forms.beneform4j.core.dao.exception.DaoExceptionCodes;
import com.forms.beneform4j.core.dao.util.DBHelp.IConnectionCallback;
import com.forms.beneform4j.core.util.exception.Throw;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 数据库元信息工具类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-2-23<br>
 */
public abstract class MetaUtilsImpl {

    private static final MetaUtilsImpl instance = new MetaUtilsImpl() {};

    private MetaUtilsImpl() {}

    static MetaUtilsImpl getInstance() {
        return instance;
    }

    /**
     * 获取数据库元信息
     * 
     * @param dataSource 数据源
     * @return 数据库元信息
     */
    public DatabaseMetaData getDatabaseMetaData(DataSource dataSource) {
        return DBHelp.Connection.doInConnection(dataSource, new IConnectionCallback<DatabaseMetaData>() {
            @Override
            public DatabaseMetaData callback(Connection conn) {
                return getDatabaseMetaData(conn);
            }
        });
    }

    /**
     * 获取数据库元信息
     * 
     * @param conn 数据库连接
     * @return 数据库元信息
     */
    public DatabaseMetaData getDatabaseMetaData(Connection conn) {
        try {
            return conn.getMetaData();
        } catch (SQLException e) {
            throw Throw.createRuntimeException(DaoExceptionCodes.BF020010, e);
        }
    }

    /**
     * 获取数据库产品名称
     * 
     * @param dataSource 数据源
     * @return 数据库产品名称
     */
    public String getDatabaseProductName(DataSource dataSource) {
        return DBHelp.Connection.doInConnection(dataSource, new IConnectionCallback<String>() {
            @Override
            public String callback(Connection conn) {
                return getDatabaseProductName(conn);
            }
        });
    }

    /**
     * 获取数据库产品名称
     * 
     * @param conn 数据库连接
     * @return 数据库产品名称
     */
    public String getDatabaseProductName(Connection conn) {
        try {
            return conn.getMetaData().getDatabaseProductName();
        } catch (SQLException e) {
            throw Throw.createRuntimeException(DaoExceptionCodes.BF020010, e);
        }
    }

    /**
     * 判断表是否存在
     * 
     * @param dataSource 数据源
     * @param tableName 表名称
     * @return 表是否存在
     */
    public boolean exist(DataSource dataSource, final String tableName) {
        return DBHelp.Connection.doInConnection(dataSource, new IConnectionCallback<Boolean>() {
            @Override
            public Boolean callback(Connection conn) {
                return exist(conn, tableName);
            }
        });
    }

    /**
     * 判断表是否存在
     * 
     * @param conn 数据库连接
     * @param tableName 表名称
     * @return 表是否存在
     */
    public boolean exist(Connection conn, String tableName) {
        ResultSet rs = null;
        try {
            if (null == tableName) {
                return false;
            }
            DatabaseMetaData meta = conn.getMetaData();
            String[] t = getCatalogSchemaTable(tableName);
            rs = meta.getTables(t[0], t[1], t[2], new String[] {"TABLE"});
            return rs.next();
        } catch (SQLException e) {
            throw Throw.createRuntimeException(DaoExceptionCodes.BF020011, e);
        } finally {
            DBHelp.Closer.close(rs);
        }
    }

    /**
     * 判断一组表是否存在
     * 
     * @param dataSource 数据源
     * @param tableNames 表名称数组
     * @return 表示表是否存在的数组，和表名称一一对应
     */
    public boolean[] exist(DataSource dataSource, final String[] tableNames) {
        return DBHelp.Connection.doInConnection(dataSource, new IConnectionCallback<boolean[]>() {
            @Override
            public boolean[] callback(Connection conn) {
                return exist(conn, tableNames);
            }
        });
    }

    /**
     * 判断一组表是否存在
     * 
     * @param conn 数据库连接
     * @param tableNames 表名称数组
     * @return 表示表是否存在的数组，和表名称一一对应
     */
    public boolean[] exist(Connection conn, String[] tableNames) {
        if (null == tableNames || 0 == tableNames.length) {
            return null;
        }
        boolean[] r = new boolean[tableNames.length];
        for (int i = 0, l = tableNames.length; i < l; i++) {
            String tableName = tableNames[i];
            r[i] = exist(conn, tableName);
        }
        return r;
    }

    private String[] getCatalogSchemaTable(String tableName) {
        String catalog = null;
        String schema = null;
        int index = tableName.lastIndexOf('.');
        if (-1 != index) {
            schema = tableName.substring(0, index);
            tableName = tableName.substring(index + 1);
            index = schema.lastIndexOf('.');
            if (-1 != index) {
                catalog = schema.substring(0, index);
                schema = schema.substring(index + 1);
            }
        }
        return new String[] {catalog, schema, tableName};
    }
}
