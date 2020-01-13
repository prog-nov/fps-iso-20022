package com.forms.beneform4j.core.dao.jndi;

import java.util.Properties;

import javax.sql.DataSource;

import com.forms.beneform4j.core.dao.dialect.IDialect;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 数据源抽象接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24<br>
 */
public interface IJndi {

    /**
     * 获取数据源名称
     * <p>
     * 默认为spring配置中的beanId
     * </p>
     * 
     * @return 数据源名称
     */
    public String getName();

    /**
     * 是否默认数据源
     * <p>
     * 默认情况下，如果spring配置中的beanId等于dataSource(不区分大小写)则设置为默认数据源，如果不存在dataSource，则设置第一个数据源为默认数据源
     * </p>
     * 
     * @return 是否默认数据源
     */
    public boolean isDefault();

    /**
     * 获取数据库方言
     * <p>
     * 根据数据库产品名称自动判断哪个数据库方言
     * </p>
     * 
     * @return 和数据源对应的数据库方言
     */
    public IDialect getDialect();

    /**
     * 获取数据源
     * 
     * @return 对应的数据源
     */
    public DataSource getDataSource();

    /**
     * 获取属性
     * 
     * @return
     */
    public Properties getProperties();
}
