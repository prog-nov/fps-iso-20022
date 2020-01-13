package com.forms.beneform4j.core.dao.util;

import java.sql.Connection;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 数据库帮助类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24<br>
 */
public class DBHelp {

    /**
     * 驱动
     */
    public static final DriverUtilsImpl Driver = DriverUtilsImpl.getInstance();

    /**
     * 元信息
     */
    public static final MetaUtilsImpl Meta = MetaUtilsImpl.getInstance();

    /**
     * 连接
     */
    public static final ConnectionUtilsImpl Connection = ConnectionUtilsImpl.getInstance();

    /**
     * 语句
     */
    public static final StatementUtilsImpl Statement = StatementUtilsImpl.getInstance();

    /**
     * 结果集
     */
    public static final ResultSetUtilsImpl ResultSet = ResultSetUtilsImpl.getInstance();

    /**
     * 关闭
     */
    public static final CloseUtilsImpl Closer = CloseUtilsImpl.getInstance();

    /**
     * 数据库连接回调接口
     */
    public interface IConnectionCallback<T> {

        /**
         * 回调方法
         * 
         * @param conn 数据库连接
         * @return 回调返回的结果
         */
        T callback(Connection conn);
    }
}
