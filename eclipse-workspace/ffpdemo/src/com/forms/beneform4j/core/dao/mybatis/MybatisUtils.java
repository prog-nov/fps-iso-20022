package com.forms.beneform4j.core.dao.mybatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.SqlSessionTemplate;

import com.forms.beneform4j.core.dao.IDaoTemplate;
import com.forms.beneform4j.core.dao.exception.DaoExceptionCodes;
import com.forms.beneform4j.core.dao.sql.SqlManager;
import com.forms.beneform4j.core.util.exception.Throw;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : Mybatis工具类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24<br>
 */
public class MybatisUtils {

    /**
     * 映射缓存
     */
    private static final List<DaoTemplateMapper> mappers = new ArrayList<DaoTemplateMapper>();

    /**
     * sql-id与映射的缓存
     */
    private static final Map<String, DaoTemplateMapper> mapper = new HashMap<String, DaoTemplateMapper>();

    /**
     * 注册dao模板
     * 
     * @param daoTemplate
     */
    /* package */ static void register(MybatisDaoTemplate daoTemplate) {
        DaoTemplateMapper m = new DaoTemplateMapper(daoTemplate);
        for (Object ms : m.configuration.getMappedStatements()) {// Mybatis返回的集合中存在Ambiguity对象
            if (ms instanceof MappedStatement) {
                mapper.put(((MappedStatement) ms).getId(), m);
            }
        }
        mappers.add(m);
    }

    /**
     * 根据SQL-ID查找对应的数据源对象
     * 
     * @param statement sqlID
     * @return 数据源对象
     */
    public static DataSource getDataSource(String statement) {
        return getJndiSqlSessionTemplateMapper(statement).dataSource;
    }

    /**
     * 根据SQL-ID查找对应的Mybatis配置对象
     * 
     * @param statement SQL-ID
     * @return Mybatis配置对象
     */
    public static Configuration getConfiguration(String statement) {
        return getJndiSqlSessionTemplateMapper(statement).configuration;
    }

    /**
     * 根据SQL-ID查找对应的Mybatis会话模板对象
     * 
     * @param statement SQL-ID
     * @return Mybatis会话模板对象
     */
    public static SqlSessionTemplate getSqlSession(String statement) {
        return getJndiSqlSessionTemplateMapper(statement).sqlSessionTemplate;
    }

    /**
     * 根据SQL-ID查找对应的Mybatis会话模板对象(批量模式)
     * 
     * @param statement SQL-ID
     * @return Mybatis会话模板对象(批量模式)
     */
    public static SqlSessionTemplate getBatchSqlSession(String statement) {
        return getJndiSqlSessionTemplateMapper(statement).batchSqlSessionTemplate;
    }

    /**
     * 根据SQL-ID查找对应的Mybatis映射对象
     * 
     * @param statement SQL-ID
     * @return Mybatis映射对象
     */
    public static MappedStatement getMappedStatement(String statement) {
        return getJndiSqlSessionTemplateMapper(statement).configuration.getMappedStatement(statement);
    }

    /**
     * 根据SQL-ID查找对应的Dao模板对象
     * 
     * @param statement SQL-ID
     * @return Dao模板对象
     */
    public static IDaoTemplate getDaoTemplate(String statement) {
        return getJndiSqlSessionTemplateMapper(statement).dao;
    }

    /**
     * 打开批量执行模式
     */
    public static void openBatchType() {
        for (DaoTemplateMapper mapper : mappers) {
            mapper.dao.openBatchType();
        }
    }

    /**
     * 恢复打开批量模式之前的执行模式
     */
    public static void resetExecutorType() {
        for (DaoTemplateMapper mapper : mappers) {
            mapper.dao.resetExecutorType();
        }
    }

    /**
     * 根据SQL-ID查找内部映射对象
     * 
     * @param statement SQL-ID
     * @return 内部映射对象
     */
    private static DaoTemplateMapper getJndiSqlSessionTemplateMapper(String statement) {
        statement = SqlManager.getExecuteSqlId(statement);
        DaoTemplateMapper jdtm = mapper.get(statement);
        if (null == jdtm) {
            Throw.throwRuntimeException(DaoExceptionCodes.BF020016, statement);
        }
        return jdtm;
    }

    /**
     * 内部映射对象
     */
    private static class DaoTemplateMapper {
        private final IDaoTemplate dao;
        private final DataSource dataSource;
        private final SqlSessionTemplate sqlSessionTemplate;
        private final SqlSessionTemplate batchSqlSessionTemplate;
        private final Configuration configuration;

        private DaoTemplateMapper(MybatisDaoTemplate daoTemplate) {
            this.dao = daoTemplate;
            this.sqlSessionTemplate = daoTemplate.getSqlSession();
            this.batchSqlSessionTemplate = daoTemplate.getBatchSqlSession();
            this.configuration = sqlSessionTemplate.getConfiguration();
            this.dataSource = this.configuration.getEnvironment().getDataSource();
        }
    }
}
