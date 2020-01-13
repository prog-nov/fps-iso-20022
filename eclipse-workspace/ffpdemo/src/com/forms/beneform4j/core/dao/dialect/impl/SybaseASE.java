package com.forms.beneform4j.core.dao.dialect.impl;

import java.util.regex.Pattern;

import com.forms.beneform4j.core.dao.dialect.IDialect;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : Sybase ASE方言<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2013-10-6<br>
 */
public abstract class SybaseASE extends AbstractDialect {

    private final static Pattern SELECT = Pattern.compile("select", Pattern.CASE_INSENSITIVE);

    // private final static Pattern FROM = Pattern.compile("from", Pattern.CASE_INSENSITIVE);

    // private final static Pattern ORDER = Pattern.compile("order by", Pattern.CASE_INSENSITIVE);

    private static final IDialect instance = new SybaseASE() {};// 唯一实例

    /**
     * 构造函数私有化
     */
    private SybaseASE() {
        super.setType(DBType.ASE);
        super.setDriverClassName("com.sybase.jdbc3.jdbc.SybDriver");
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
        int index = sql.toLowerCase().lastIndexOf("order by");
        if (-1 != index) {
            sql = sql.substring(0, index);
        }
        // sql = ORDER.matcher(sql).replaceAll("");//去掉子查询中的order by语句
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
        sql = SELECT.matcher(sql).replaceFirst("select sybid=identity(12),");// 替换第一个匹配的select
        int index = sql.toLowerCase().indexOf("from");
        sql = sql.substring(0, index) + "into #temptable1 " + sql.substring(index);// 替换最后一个匹配的from
        // sql = FROM.matcher(sql).replaceFirst("into #temptable1 from");
        sql = " set chained off " + sql + " select * from #temptable1 where sybid> " + offset + " and sybid <= " + (offset + limit);
        sql += " drop table #temptable1 ";
        return sql;
    }
}
