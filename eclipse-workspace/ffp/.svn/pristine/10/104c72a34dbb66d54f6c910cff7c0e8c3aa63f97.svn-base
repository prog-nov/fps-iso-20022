package com.forms.beneform4j.core.dao.dialect.impl;

import com.forms.beneform4j.core.dao.dialect.IDialect;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 抽象的方言类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2013-10-6<br>
 */
public abstract class AbstractDialect implements IDialect {

    private DBType type;
    private String[] driverClassNames;

    /**
     * 获取数据库类型
     * 
     * @return
     */
    @Override
    public DBType getType() {
        return type;
    }

    /**
     * 设置数据库类型
     * 
     * @param type
     */
    protected void setType(DBType type) {
        this.type = type;
    }

    /**
     * 获取可能的数据库驱动类名
     */
    @Override
    public String[] getDriverClassNames() {
        return driverClassNames;
    }

    /**
     * 设置可能的驱动类名称
     * 
     * @param driverClassNames
     */
    protected void setDriverClassNames(String[] driverClassNames) {
        this.driverClassNames = driverClassNames;
    }

    /**
     * 设置可能的驱动类名称
     * 
     * @param driverClassName
     */
    protected void setDriverClassName(String driverClassName) {
        setDriverClassNames(new String[] {driverClassName});
    }
}
