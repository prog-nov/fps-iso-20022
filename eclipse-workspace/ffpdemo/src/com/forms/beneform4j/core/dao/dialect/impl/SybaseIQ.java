package com.forms.beneform4j.core.dao.dialect.impl;

import java.util.regex.Pattern;

import com.forms.beneform4j.core.dao.dialect.IDialect;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : Sybase IQ方言<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2013-10-6<br>
 */
public abstract class SybaseIQ extends AbstractDialect {

    private static final IDialect instance = new SybaseIQ() {};// 唯一实例

    private final static Pattern SELECT = Pattern.compile("^\\s*select\\s+", Pattern.CASE_INSENSITIVE);

    private final static Pattern FROM = Pattern.compile("\\s+from\\s+", Pattern.CASE_INSENSITIVE);

    private final static Pattern ORDER = Pattern.compile("\\s+order\\s+by\\s+(?![^\\)]+\\))[^\\)]+$", Pattern.CASE_INSENSITIVE);

    /**
     * 构造函数私有化
     */
    private SybaseIQ() {
        super.setType(DBType.IQ);
        super.setDriverClassName("com.sybase.jdbc3.jdbc.SybDriver");
    }

    /**
     * 获取唯一实例
     */
    public static IDialect getSingleInstance() {
        return instance;
    }

    public String getTotalSql(String sql) {
        StringBuilder rs = new StringBuilder();
        sql = ORDER.matcher(sql).replaceFirst("");
        rs.append("select count(1) total_ from (").append(sql).append(") total_ ");
        return rs.toString();
    }

    public String getScopeSql(String sql, long offset, int limit) {
        String tempTableName = "#temptablename";
        sql = SELECT.matcher(sql).replaceFirst("SELECT TOP " + (offset + limit) + " ");
        sql = FROM.matcher(sql).replaceFirst(",PAGE_RECORD_ROW_NUMBER=NUMBER(*) INTO " + tempTableName + " FROM ");
        sql += " set chained off  SELECT * FROM " + tempTableName + " WHERE PAGE_RECORD_ROW_NUMBER > " + offset;
        sql += " drop table " + tempTableName;
        return sql;
    }
}
