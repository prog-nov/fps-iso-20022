package com.forms.beneform4j.core.dao.dialect.impl;

import com.forms.beneform4j.core.dao.dialect.IDialect;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : H2方言<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2015-7-21<br>
 */
public abstract class H2 extends AbstractDialect {

    private static final IDialect instance = new H2() {};// 唯一实例

    /**
     * 构造函数私有化
     */
    private H2() {
        super.setType(DBType.H2);
        super.setDriverClassName("org.h2.Driver");
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
