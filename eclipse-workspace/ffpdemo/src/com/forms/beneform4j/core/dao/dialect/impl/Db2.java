package com.forms.beneform4j.core.dao.dialect.impl;

import com.forms.beneform4j.core.dao.dialect.IDialect;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : Db2方言<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2015-7-21<br>
 */
public abstract class Db2 extends AbstractDialect {

    private static final IDialect instance = new Db2() {};// 唯一实例

    /**
     * 构造函数私有化
     */
    private Db2() {
        super.setType(DBType.DB2);
        super.setDriverClassName("com.ibm.db2.jcc.DB2Driver");
    }

    /**
     * 获取唯一实例
     */
    public static IDialect getSingleInstance() {
        return instance;
    }

    /**
     * 生成计算总记录数的SQL
     * 
     * @param sql
     * @return
     */
    public String getTotalSql(String sql) {
        StringBuilder rs = new StringBuilder();
        rs.append("select count(1) total_ from (").append(sql).append(") total_ ");
        return rs.toString();
    }

    /**
     * 获取查询指定范围记录的SQL
     * 
     * @param sql
     * @param offset
     * @param limit
     * @return
     */
    public String getScopeSql(String sql, long offset, int limit) {
        StringBuilder scope = new StringBuilder();
        scope.append("select * from ( select row_.*, row_number() over() rownum_ from ( ");
        scope.append(sql);
        scope.append(" ) row_ ) a where rownum_ > ").append(offset).append(" and rownum_ <= ").append(offset + limit);
        return scope.toString();
    }
}
