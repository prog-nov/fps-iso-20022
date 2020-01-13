package com.forms.beneform4j.core.dao.jndi;

import java.sql.Connection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import com.forms.beneform4j.core.Beneform4jConfig;
import com.forms.beneform4j.core.dao.dialect.IDialect;
import com.forms.beneform4j.core.dao.exception.DaoExceptionCodes;
import com.forms.beneform4j.core.dao.util.DBHelp;
import com.forms.beneform4j.core.dao.util.DBHelp.IConnectionCallback;
import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.exception.Throw;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 数据源抽象对象的管理帮助类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24<br>
 */
@Component("JndiManager")
public class JndiManager implements ApplicationContextAware, InitializingBean {

    private static IJndi DEFAULT = null;
    // private static AtomicBoolean monitor = new AtomicBoolean(false);
    private static final Map<String, IJndi> jndiCache = new LinkedHashMap<String, IJndi>();
    private static final Map<DataSource, PlatformTransactionManager> dataSources = new HashMap<DataSource, PlatformTransactionManager>();
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        JndiManager.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, DataSource> dataSources = applicationContext.getBeansOfType(DataSource.class);
        if (null != dataSources && !dataSources.isEmpty()) {
            for (Iterator<String> i = dataSources.keySet().iterator(); i.hasNext();) {
                String beanId = i.next();
                register(beanId, dataSources.get(beanId));
            }
        }
        initialDefaultJndi();
    }

    private static void register(String beanName, DataSource dataSource) {
        if (dataSources.containsKey(dataSource)) {
            return;
        } else {
            JndiImpl jndi = new JndiImpl();
            jndi.setName(beanName);
            jndi.setDataSource(dataSource);
            jndiCache.put(beanName, jndi);
            dataSources.put(dataSource, resolverTransactionManager(dataSource));
        }
    }

    /* package */static void register(JndiImpl jndi) {
        DataSource dataSource = jndi.getDataSource();
        if (!dataSources.containsKey(dataSource)) {
            jndiCache.put(jndi.getName(), jndi);
            dataSources.put(dataSource, resolverTransactionManager(dataSource));
        }
    }

    /**
     * 获取所有数据源
     * 
     * @return
     */
    public static Map<String, IJndi> getJndis() {
        return Collections.unmodifiableMap(jndiCache);
    }

    /**
     * 获取默认Jndi
     * 
     * @return 默认的数据源抽象对象
     */
    public static IJndi getDefaultJndi() {
        // if(!monitor.get()){
        // synchronized(monitor){
        // if(!monitor.get()){
        // //initialDefaultJndi();
        // if(DEFAULT != null){
        // monitor.set(true);
        // }
        // }
        // }
        // }
        return DEFAULT;
    }

    /**
     * 获取事务管理器
     * 
     * @param dataSource
     * @return
     */
    public static PlatformTransactionManager getTransactinManager(DataSource dataSource) {
        return dataSources.get(dataSource);
    }

    /**
     * 根据名称获取数据源抽象对象
     * 
     * @param name
     * @return 和name相对应的数据源抽象对象，如果不存在，抛出平台运行时异常
     */
    public static IJndi getJndi(String name) {
        if (CoreUtils.isBlank(name)) {
            return getDefaultJndi();
        } else {
            IJndi jndi = jndiCache.get(name);
            if (null == jndi) {
                throw Throw.createRuntimeException(DaoExceptionCodes.BF020007, name);
            }
            return jndi;
        }
    }

    /**
     * 根据数据源获取实例
     * 
     * @param dataSource 数据源
     * @return 和数据源相对应的数据源抽象对象
     */
    public static IJndi getJndi(DataSource dataSource) {
        for (IJndi j : jndiCache.values()) {
            if (dataSource.equals(j.getDataSource())) {
                return j;
            }
        }
        return getDefaultJndi();
    }

    /**
     * 根据数据源抽象对象获取数据库方言
     * 
     * @param jndi 数据源抽象对象
     * @return 数据库方言
     */
    public static IDialect getDialect(IJndi jndi) {
        return jndi.getDialect();
    }

    /**
     * 根据数据源获取方言
     * 
     * @param dataSource 数据源
     * @return 数据库方言
     */
    public static IDialect getDialect(DataSource dataSource) {
        IJndi jndi = getJndi(dataSource);
        if (null == jndi) {
            return DBHelp.Connection.doInConnection(dataSource, new IConnectionCallback<IDialect>() {
                @Override
                public IDialect callback(Connection conn) {
                    return getDialect(conn);
                }
            });
        } else {
            return jndi.getDialect();
        }
    }

    /**
     * 根据数据库连接获取方言
     * 
     * @param conn 数据库连接
     * @return 数据库方言
     */
    public static IDialect getDialect(Connection conn) {
        String databaseProductName = DBHelp.Meta.getDatabaseProductName(conn);
        return getDialect(databaseProductName);
    }

    /**
     * 根据数据库产品名称获取数据库方言
     * 
     * @param databaseProductName 数据库产品名称
     * @return 数据库方言
     */
    private static IDialect getDialect(String databaseProductName) {
        Map<String, IDialect> databaseProductNameDialectMapping = Beneform4jConfig.getDatabaseProductNameDialectMapping();
        if (CoreUtils.isBlank(databaseProductName)) {
            Throw.throwRuntimeException(DaoExceptionCodes.BF020002);
        } else if (null == databaseProductNameDialectMapping) {
            Throw.throwRuntimeException(DaoExceptionCodes.BF020003);
        } else {
            for (String key : databaseProductNameDialectMapping.keySet()) {
                if (null != key && -1 != databaseProductName.toLowerCase().indexOf(key.toLowerCase())) {
                    return databaseProductNameDialectMapping.get(key);
                }
            }
            Throw.throwRuntimeException(DaoExceptionCodes.BF020004, databaseProductName);
        }
        return null;
    }

    private static void initialDefaultJndi() {
        IJndi first = null;
        IJndi defaultName = null;
        for (String name : jndiCache.keySet()) {
            IJndi jndi = jndiCache.get(name);
            if (jndi.isDefault()) {
                DEFAULT = jndi;
                return;
            }
            if (null == first) {
                first = jndi;
            }
            if (null == defaultName && "dataSource".equals(name)) {
                defaultName = jndi;
            }
        }
        if (null == DEFAULT) {
            if (null != defaultName) {
                DEFAULT = defaultName;
                return;
            }
            if (null != first) {
                DEFAULT = first;
                return;
            }
        }
        if (DEFAULT instanceof JndiImpl) {
            ((JndiImpl) DEFAULT).setDefault(true);
        }
    }

    private static PlatformTransactionManager resolverTransactionManager(DataSource ds) {
        Map<String, PlatformTransactionManager> tms = applicationContext.getBeansOfType(PlatformTransactionManager.class);
        if (null != tms) {
            for (PlatformTransactionManager tm : tms.values()) {
                if (tm instanceof DataSourceTransactionManager) {
                    DataSourceTransactionManager dstm = (DataSourceTransactionManager) tm;
                    if (ds.equals(dstm.getDataSource())) {
                        return dstm;
                    }
                }
            }
        }
        return null;
    }
}
