package com.forms.beneform4j.core.dao.dialect;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 数据库方言接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public interface IDialect {

    /**
     * 数据库类型
     */
    enum DBType {
        Oracle, DB2, H2, MySql, ASE, IQ, SQLITE
    }

    /**
     * 获取数据库类型
     * 
     * @return 数据库类型枚举常量
     */
    public DBType getType();

    /**
     * 获取可能的数据库驱动类名称
     * 
     * @return 驱动类名数组
     */
    public String[] getDriverClassNames();

    /**
     * 生成计算总记录数的SQL
     * 
     * @param sql 原始SQL
     * @return 计算总记录数的SQL
     */
    public String getTotalSql(String sql);

    /**
     * 获取查询指定范围记录的SQL
     * 
     * @param sql 原始SQL
     * @param offset 返回的开始记录索引
     * @param limit 查询的数据大小
     * @return 查询第(offset, offset + limit]条记录的SQL，索引从1开始
     */
    public String getScopeSql(String sql, long offset, int limit);
}
