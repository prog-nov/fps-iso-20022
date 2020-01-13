package com.forms.beneform4j.core.dao.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.forms.beneform4j.core.util.exception.Throw;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 数据库连接关闭工具类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-2-23<br>
 */
public abstract class CloseUtilsImpl {

    private static final CloseUtilsImpl instance = new CloseUtilsImpl() {};

    private CloseUtilsImpl() {}

    static CloseUtilsImpl getInstance() {
        return instance;
    }

    /**
     * 关闭连接
     * 
     * @param conn
     */
    public void close(Connection conn) {
        try {
            if (null != conn && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            Throw.throwRuntimeException(e);
        }
    }

    /**
     * 关闭结果集
     * 
     * @param rs
     */
    public void close(ResultSet rs) {
        try {
            if (null != rs) {
                rs.close();
            }
        } catch (SQLException e) {
            Throw.throwRuntimeException(e);
        }
    }

    /**
     * 关闭语句
     * 
     * @param stat
     */
    public void close(Statement stat) {
        try {
            if (null != stat) {
                stat.close();
            }
        } catch (SQLException e) {
            Throw.throwRuntimeException(e);
        }
    }

    /**
     * 关闭语句和结果集
     * 
     * @param stat
     * @param rs
     */
    public void close(Statement stat, ResultSet rs) {
        close(rs);
        close(stat);
    }

    /**
     * 关闭连接、语句和结果集
     * 
     * @param conn
     * @param stat
     * @param rs
     */
    public void close(Connection conn, Statement stat, ResultSet rs) {
        close(rs);
        close(stat);
        close(conn);
    }

    /**
     * 关闭连接和语句
     * 
     * @param conn
     * @param stat
     */
    public void close(Connection conn, Statement stat) {
        close(stat);
        close(conn);
    }
}
