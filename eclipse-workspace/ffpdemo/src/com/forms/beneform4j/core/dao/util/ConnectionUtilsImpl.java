package com.forms.beneform4j.core.dao.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;

import com.forms.beneform4j.core.dao.dialect.IDialect;
import com.forms.beneform4j.core.dao.dialect.impl.MySQL;
import com.forms.beneform4j.core.dao.dialect.impl.Oracle;
import com.forms.beneform4j.core.dao.jndi.JndiManager;
import com.forms.beneform4j.core.dao.util.DBHelp.IConnectionCallback;
import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.core.util.logger.CommonLogger;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 数据库连接工具类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-2-23<br>
 */
public abstract class ConnectionUtilsImpl {

    private static final ConnectionUtilsImpl instance = new ConnectionUtilsImpl() {};

    private ConnectionUtilsImpl() {}

    static ConnectionUtilsImpl getInstance() {
        return instance;
    }

    /**
     * 获取数据库连接
     * 
     * @param dataSource
     * @return
     */
    public Connection get(DataSource dataSource) {
        try {
            return DataSourceUtils.getConnection(dataSource);
        } catch (Exception e) {
            throw Throw.createRuntimeException(e);
        }
    }

    /**
     * 释放数据库连接
     * 
     * @param dataSource
     * @return
     */
    public void release(Connection conn, DataSource dataSource) {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }

    /**
     * 获取数据库直连连接
     * 
     * @param driver
     * @param url
     * @param username
     * @param password
     * @return
     */
    public Connection get(String driver, String url, String username, String password) {
        try {
            DBHelp.Driver.load(driver);
            return DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            throw Throw.createRuntimeException(e);
        }
    }

    /**
     * 提交
     * 
     * @param conn
     */
    public void commit(Connection conn) {
        try {
            if (null != conn) {
                conn.commit();
            }
        } catch (SQLException e) {
            throw Throw.createRuntimeException(e);
        }
    }

    /**
     * 回滚
     * 
     * @param conn
     */
    public void rollback(Connection conn) {
        try {
            if (null != conn) {
                conn.rollback();
            }
        } catch (SQLException e) {
            throw Throw.createRuntimeException(e);
        }
    }

    /**
     * 开始事务
     * 
     * @param conn
     * @param newTransaction
     */
    public void beginTransaction(Connection conn, boolean newTransaction) {
        if (newTransaction) {
            this.commit(conn);
            this.setAutoCommit(conn, false);
        } else {
            this.setAutoCommit(conn, false);
        }
    }

    /**
     * 结束事务
     * 
     * @param conn
     */
    public void endTransaction(Connection conn) {
        this.commit(conn);
        this.setAutoCommit(conn, true);
    }

    /**
     * 设置是否自动提交
     * 
     * @param conn
     * @param autoCommit
     */
    public void setAutoCommit(Connection conn, boolean autoCommit) {
        try {
            if (null != conn) {
                conn.setAutoCommit(autoCommit);
            }
        } catch (SQLException e) {
            throw Throw.createRuntimeException(e);
        }
    }

    /**
     * 在数据库连接中执行
     * 
     * @param dataSource 数据源
     * @param callback 回调接口实例
     * @return 回调返回的结果
     */
    public <T> T doInConnection(DataSource dataSource, IConnectionCallback<T> callback) {
        Connection conn = null;
        try {
            conn = DataSourceUtils.getConnection(dataSource);
            return callback.callback(conn);
        } finally {
            DataSourceUtils.releaseConnection(conn, dataSource);
        }
    }

    /**
     * 执行文件中的SQL，适用数据库db2、h2、hsql、mssql、mysql、oracle、postgres
     * 
     * @param connection 数据库连接
     * @param resourceLocation SQL文件路径
     */
    public void executeResourceLocation(Connection connection, String resourceLocation) {
        CommonLogger.info("execute resourceName: " + resourceLocation);
        String sqlStatement = null;
        try {
            Exception exception = null;
            byte[] bytes = readInputStream(resourceLocation);
            String ddlStatements = new String(bytes);
            IDialect dialect = JndiManager.getDialect(connection);

            // Special DDL handling for certain databases
            if (dialect.equals(MySQL.getSingleInstance())) {
                DatabaseMetaData databaseMetaData = connection.getMetaData();
                int majorVersion = databaseMetaData.getDatabaseMajorVersion();
                int minorVersion = databaseMetaData.getDatabaseMinorVersion();
                CommonLogger.info("Found MySQL: majorVersion=" + majorVersion + " minorVersion=" + minorVersion);

                // Special care for MySQL < 5.6
                if (majorVersion <= 5 && minorVersion < 6) {
                    ddlStatements = updateDdlForMySqlVersionLowerThan56(ddlStatements);
                }
            }

            BufferedReader reader = new BufferedReader(new StringReader(ddlStatements));
            String line = readNextTrimmedLine(reader);
            boolean inOraclePlsqlBlock = false;
            while (line != null) {
                if (line.startsWith("#")) {
                    CommonLogger.debug(line.substring(1).trim());

                } else if (line.startsWith("--")) {
                    CommonLogger.debug(line.substring(2).trim());

                } else if (line.length() > 0) {

                    if (dialect.equals(Oracle.getSingleInstance()) && line.startsWith("begin")) {
                        inOraclePlsqlBlock = true;
                        sqlStatement = addSqlStatementPiece(sqlStatement, line);

                    } else if ((line.endsWith(";") && inOraclePlsqlBlock == false) || (line.startsWith("/") && inOraclePlsqlBlock == true)) {

                        if (inOraclePlsqlBlock) {
                            inOraclePlsqlBlock = false;
                        } else {
                            sqlStatement = addSqlStatementPiece(sqlStatement, line.substring(0, line.length() - 1));
                        }

                        Statement jdbcStatement = connection.createStatement();
                        try {
                            // no logging needed as the connection will log it
                            CommonLogger.debug("SQL: " + sqlStatement);
                            jdbcStatement.execute(sqlStatement);
                            jdbcStatement.close();
                        } catch (Exception e) {
                            if (exception == null) {
                                exception = e;
                            }
                            CommonLogger.error("problem during statement " + sqlStatement, e);
                        } finally {
                            sqlStatement = null;
                        }
                    } else {
                        sqlStatement = addSqlStatementPiece(sqlStatement, line);
                    }
                }

                line = readNextTrimmedLine(reader);
            }

            if (exception != null) {
                throw exception;
            }

            CommonLogger.debug("execute successful");

        } catch (Exception e) {
            Throw.throwRuntimeException(e);
        }
    }

    private byte[] readInputStream(String inputStreamName) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[16 * 1024];
        InputStream inputStream = null;
        try {
            inputStream = CoreUtils.getResource(inputStreamName).getInputStream();
            int bytesRead = inputStream.read(buffer);
            while (bytesRead != -1) {
                outputStream.write(buffer, 0, bytesRead);
                bytesRead = inputStream.read(buffer);
            }
        } catch (Exception e) {
            Throw.throwRuntimeException("couldn't read input stream " + inputStreamName, e);
        } finally {
            CoreUtils.closeQuietly(inputStream);
        }
        return outputStream.toByteArray();
    }

    private String updateDdlForMySqlVersionLowerThan56(String ddlStatements) {
        return ddlStatements.replace("timestamp(3)", "timestamp").replace("datetime(3)", "datetime").replace("TIMESTAMP(3)", "TIMESTAMP").replace("DATETIME(3)", "DATETIME");
    }

    private String addSqlStatementPiece(String sqlStatement, String line) {
        if (sqlStatement == null) {
            return line;
        }
        return sqlStatement + " \n" + line;
    }

    private String readNextTrimmedLine(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        if (line != null) {
            line = line.trim();
        }
        return line;
    }
}
