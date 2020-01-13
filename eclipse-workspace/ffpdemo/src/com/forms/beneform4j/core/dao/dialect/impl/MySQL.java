package com.forms.beneform4j.core.dao.dialect.impl;

import com.forms.beneform4j.core.dao.dialect.IDialect;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : MySQL方言<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2013-10-6<br>
 */
public abstract class MySQL extends AbstractDialect {

    private static final IDialect instance = new MySQL() {};// 唯一实例

    /**
     * 构造函数私有化
     */
    private MySQL() {
        super.setType(DBType.MySql);
        super.setDriverClassName("com.mysql.jdbc.Driver");
    }

    /**
     * 获取唯一实例
     */
    public static IDialect getSingleInstance() {
        return instance;
    }

    public String getTotalSql(String sql) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT COUNT(1) FROM (").append(sql).append(") T");
        return sb.toString();
    }

    public String getScopeSql(String sql, long offset, int limit) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM (").append(sql).append(") T LIMIT ").append(offset).append(",").append(limit);
        return sb.toString();
    }
}
