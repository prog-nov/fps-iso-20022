package com.forms.beneform4j.core;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.forms.beneform4j.core.dao.dialect.IDialect;
import com.forms.beneform4j.core.dao.dialect.impl.Db2;
import com.forms.beneform4j.core.dao.dialect.impl.H2;
import com.forms.beneform4j.core.dao.dialect.impl.MySQL;
import com.forms.beneform4j.core.dao.dialect.impl.Oracle;
import com.forms.beneform4j.core.dao.dialect.impl.SybaseASE;
import com.forms.beneform4j.core.dao.dialect.impl.SybaseIQ;
import com.forms.beneform4j.core.dao.mybatis.mapper.IMapperMethodExecutor;
import com.forms.beneform4j.core.dao.sql.interceptor.ISqlInterceptor;
import com.forms.beneform4j.core.dao.sql.mapper.ISqlMapperStrategy;
import com.forms.beneform4j.core.dao.sql.resolver.ISqlResolver;
import com.forms.beneform4j.core.service.request.IRequestInfoFactory;
import com.forms.beneform4j.core.util.annotation.Configable;
import com.forms.beneform4j.core.util.aop.IAopInterceptor;
import com.forms.beneform4j.core.util.config.BaseConfig;
import com.forms.beneform4j.security.core.session.ISessionManager;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 平台配置类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24<br>
 */
public class Beneform4jConfig extends BaseConfig {

    private static final LinkedHashMap<String, IDialect> defaultDatabaseProductNameDialectMapping = new LinkedHashMap<String, IDialect>();
    static {
        defaultDatabaseProductNameDialectMapping.put("oracle", Oracle.getSingleInstance());
        defaultDatabaseProductNameDialectMapping.put("mysql", MySQL.getSingleInstance());
        defaultDatabaseProductNameDialectMapping.put("db2", Db2.getSingleInstance());
        defaultDatabaseProductNameDialectMapping.put("h2", H2.getSingleInstance());
        defaultDatabaseProductNameDialectMapping.put("ADAPTIVE SERVER ENTERPRISE", SybaseASE.getSingleInstance());
        defaultDatabaseProductNameDialectMapping.put("IQ", SybaseIQ.getSingleInstance());
    }

    /**
     * 平台表的前缀
     */
    @Configable
    private static String beneform4jTablePrefix = "";

    /**
     * 总记录条数的参数名称，默认为totalRecords
     */
    @Configable
    private static String totalRecordsParamName;

    /**
     * 分页大小的参数名称，默认为pageSize
     */
    @Configable
    private static String pageSizeParamName;

    /**
     * 默认分页大小，默认为15
     */
    @Configable
    private static int defaultPageSize = EQUAL_EMPTY_INT_VALUE;

    /**
     * 当前页数的参数名称，默认为page
     */
    @Configable
    private static String currentPageParamName;

    /**
     * 表示开发模式的配置项名称
     */
    @Configable
    private static String devModeConfigName;

    /**
     * 表示语言的配置项名称
     */
    @Configable
    private static String localeConfigName;

    /**
     * 表示主题的配置项名称
     */
    @Configable
    private static String themeConfigName;

    /**
     * 默认主题
     */
    @Configable
    private static String defaultTheme;

    /**
     * sqlId映射（通过注入该映射，可以替换执行平台中已打包好的SQL）
     */
    @Configable
    private static Map<String, String> sqlIdMapping;

    /**
     * SQL执行前的拦截器
     */
    @Configable
    private static List<ISqlInterceptor> sqlInterceptors;

    /**
     * SQL参数设置前的参数解析器
     */
    @Configable
    private static List<ISqlResolver> sqlResolvers;

    /**
     * MapperMethod拦截执行器
     */
    @Configable
    private static List<IMapperMethodExecutor> mapperMethodExecutors;

    /**
     * 数据库产品名称和数据库方言的映射关系
     */
    @Configable
    private static LinkedHashMap<String, IDialect> databaseProductNameDialectMapping = defaultDatabaseProductNameDialectMapping;

    /**
     * Mybatis的日志类型映射，根据输出的日志内容区分属于哪一种类型，然后可以在log4j中添加该类型的日志控制开关
     */
    @Configable
    private static Map<Pattern, String> mybatisLogTypeMapping;

    /**
     * SQL-ID自动映射策略
     */
    @Configable
    private static ISqlMapperStrategy sqlMapperStrategy;

    /**
     * 请求信息工厂
     */
    @Configable
    private static IRequestInfoFactory requestInfoFactory;

    /**
     * 会话管理器
     */
    @Configable
    private static ISessionManager sessionManager;

    /**
     * 服务层AOP拦截器
     */
    @Configable
    private static List<IAopInterceptor> serviceAopInterceptors;

    /**
     * 校验
     */
    @Override
    public void validate() {
        super.validate();

        // 执行当前配置类中的校验
    }

    /**
     * 获取SQL-ID映射
     * 
     * @return 原SQL-ID和新SQL-ID的映射
     */
    public static Map<String, String> getSqlIdMapping() {
        return sqlIdMapping;
    }

    /**
     * 注入sqlId映射
     * 
     * @param sqlIdMapping 原SQL-ID和新SQL-ID的映射
     */
    public void setSqlIdMapping(Map<String, String> sqlIdMapping) {
        Beneform4jConfig.sqlIdMapping = sqlIdMapping;
    }

    /**
     * 获取平台表的前缀
     * 
     * @return 配置中的平台表前缀
     */
    public static String getBeneform4jTablePrefix() {
        return getValue(beneform4jTablePrefix, "table_prefix");
    }

    /**
     * 注入平台表的前缀
     * 
     * @param beneform4jTablePrefix 表前缀
     */
    public void setBeneform4jTablePrefix(String beneform4jTablePrefix) {
        Beneform4jConfig.beneform4jTablePrefix = beneform4jTablePrefix;
    }

    public static String getTotalRecordsParamName() {
        return getValue(totalRecordsParamName, "totalRecordsParamName");
    }

    public void setTotalRecordsParamName(String totalRecordsParamName) {
        Beneform4jConfig.totalRecordsParamName = totalRecordsParamName;
    }

    public static String getPageSizeParamName() {
        return getValue(pageSizeParamName, "pageSizeParamName");
    }

    public void setPageSizeParamName(String pageSizeParamName) {
        Beneform4jConfig.pageSizeParamName = pageSizeParamName;
    }

    public static int getDefaultPageSize() {
        return getValue(defaultPageSize, "defaultPageSize");
    }

    public void setDefaultPageSize(int defaultPageSize) {
        Beneform4jConfig.defaultPageSize = defaultPageSize;
    }

    public static String getCurrentPageParamName() {
        return getValue(currentPageParamName, "currentPageParamName");
    }

    public void setCurrentPageParamName(String currentPageParamName) {
        Beneform4jConfig.currentPageParamName = currentPageParamName;
    }

    public static String getDevModeConfigName() {
        return getValue(devModeConfigName, "devModeConfigName");
    }

    public void setDevModeConfigName(String devModeConfigName) {
        Beneform4jConfig.devModeConfigName = devModeConfigName;
    }

    public static String getLocaleConfigName() {
        return getValue(localeConfigName, "localeConfigName");
    }

    public void setLocaleConfigName(String localeConfigName) {
        Beneform4jConfig.localeConfigName = localeConfigName;
    }

    public static String getThemeConfigName() {
        return getValue(themeConfigName, "themeConfigName");
    }

    public void setThemeConfigName(String themeConfigName) {
        Beneform4jConfig.themeConfigName = themeConfigName;
    }

    public static String getDefaultTheme() {
        return getValue(defaultTheme, "defaultTheme");
    }

    public void setDefaultTheme(String defaultTheme) {
        Beneform4jConfig.defaultTheme = defaultTheme;
    }

    /**
     * 获取SQL语句执行前的拦截器
     * 
     * @return SQL拦截器
     */
    public static List<ISqlInterceptor> getSqlInterceptors() {
        return getComponents(sqlInterceptors, ISqlInterceptor.class);
    }

    /**
     * 注入SQL拦截器
     * 
     * @param sqlInterceptors SQL拦截器
     */
    public void setSqlInterceptors(List<ISqlInterceptor> sqlInterceptors) {
        Beneform4jConfig.sqlInterceptors = sqlInterceptors;
    }

    /**
     * 获取SQL解析器
     * 
     * @return SQL解析器
     */
    public static List<ISqlResolver> getSqlResolvers() {
        return getComponents(sqlResolvers, ISqlResolver.class);
    }

    /**
     * 注入SQL解析器
     * 
     * @param sqlResolvers SQL解析器
     */
    public void setSqlResolvers(List<ISqlResolver> sqlResolvers) {
        Beneform4jConfig.sqlResolvers = sqlResolvers;
    }

    /**
     * 获取MapperMethod拦截执行器
     * 
     * @return
     */
    public static List<IMapperMethodExecutor> getMapperMethodExecutors() {
        return mapperMethodExecutors;
    }

    /**
     * 注入MapperMethod拦截执行器
     * 
     * @param mapperMethodExecutors
     */
    public void setMapperMethodExecutors(List<IMapperMethodExecutor> mapperMethodExecutors) {
        Beneform4jConfig.mapperMethodExecutors = mapperMethodExecutors;
    }

    /**
     * 获取数据库产品名称中关键字和方言的映射配置
     * 
     * @return 数据库产品名称中关键字和方言的映射
     */
    public static Map<String, IDialect> getDatabaseProductNameDialectMapping() {
        return databaseProductNameDialectMapping;
    }

    /**
     * 注入数据库产品名称和方言的映射配置
     * 
     * @param databaseProductNameDialectMapping 数据库产品名称中关键字和方言的映射
     */
    public void setDatabaseProductNameDialectMapping(LinkedHashMap<String, IDialect> databaseProductNameDialectMapping) {
        if (null != databaseProductNameDialectMapping && !databaseProductNameDialectMapping.isEmpty()) {
            if (null != Beneform4jConfig.databaseProductNameDialectMapping) {// 在前面插入应用配置
                databaseProductNameDialectMapping.putAll(Beneform4jConfig.databaseProductNameDialectMapping);
            }
            Beneform4jConfig.databaseProductNameDialectMapping = databaseProductNameDialectMapping;
        }
    }

    /**
     * 获取Mybatis日志内容关键字和日志名称的映射配置
     * 
     * @return Mybatis日志内容关键字和日志名称的映射配置
     */
    public static Map<Pattern, String> getMybatisLogTypeMapping() {
        return mybatisLogTypeMapping;
    }

    /**
     * 注入Mybatis日志内容关键字和日志名称的映射配置
     * 
     * @param mybatisLogTypeMapping Mybatis日志内容关键字和日志名称的映射配置
     */
    public void setMybatisLogTypeMapping(Map<Pattern, String> mybatisLogTypeMapping) {
        Beneform4jConfig.mybatisLogTypeMapping = mybatisLogTypeMapping;
    }

    /**
     * 获取根据方法名称查找执行SQL-ID的策略算法
     * 
     * @return 查找策略
     */
    public static ISqlMapperStrategy getSqlMapperStrategy() {
        return getComponent(sqlMapperStrategy, ISqlMapperStrategy.class);
    }

    /**
     * 注入根据方法名称查找执行SQL-ID的策略算法
     * 
     * @param sqlMapperStrategy 查找策略
     */
    public void setSqlMapperStrategy(ISqlMapperStrategy sqlMapperStrategy) {
        Beneform4jConfig.sqlMapperStrategy = sqlMapperStrategy;
    }

    /**
     * 获取请求信息工厂
     * 
     * @return 请求信息工厂
     */
    public static IRequestInfoFactory getRequestInfoFactory() {
        return getComponent(requestInfoFactory, IRequestInfoFactory.class);
    }

    /**
     * 设置请求信息工厂
     * 
     * @param requestInfoFactory 请求信息工厂
     */
    public void setRequestInfoFactory(IRequestInfoFactory requestInfoFactory) {
        Beneform4jConfig.requestInfoFactory = requestInfoFactory;
    }

    /**
     * 获取会话管理器
     * 
     * @return
     */
    public static ISessionManager getSessionManager() {
        return getComponent(sessionManager, ISessionManager.class);
    }

    /**
     * 设置会话管理器
     * 
     * @param sessionManager
     */
    public void setSessionManager(ISessionManager sessionManager) {
        Beneform4jConfig.sessionManager = sessionManager;
    }

    /**
     * 获取服务层AOP拦截器
     * 
     * @return
     */
    public static List<IAopInterceptor> getServiceAopInterceptors() {
        return serviceAopInterceptors;
    }

    /**
     * 设置服务层AOP拦截器
     * 
     * @param serviceAopInterceptors
     */
    public void setServiceAopInterceptors(List<IAopInterceptor> serviceAopInterceptors) {
        Beneform4jConfig.serviceAopInterceptors = serviceAopInterceptors;
    }
}
