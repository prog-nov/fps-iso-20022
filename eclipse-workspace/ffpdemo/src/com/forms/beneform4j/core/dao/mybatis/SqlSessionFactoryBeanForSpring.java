package com.forms.beneform4j.core.dao.mybatis;

import static org.springframework.util.StringUtils.hasLength;
import static org.springframework.util.StringUtils.tokenizeToStringArray;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ConfigurableApplicationContext;

import com.forms.beneform4j.core.dao.mybatis.mapper.MapperRegistry;
import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.config.BaseConfig;
import com.forms.beneform4j.core.util.logger.CommonLogger;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : Mybatis会话工厂Bean，用于集成Spring<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24<br>
 */
public class SqlSessionFactoryBeanForSpring extends SqlSessionFactoryBean {

    private boolean autoScanTypeAliases = false;

    private Class<?> baseClass;

    private String typeAliasesScanPackage = BaseConfig.getScanPackage();

    public void setTypeAliasesScanPackage(String typeAliasesScanPackage) {
        this.typeAliasesScanPackage = typeAliasesScanPackage;
    }

    public void setAutoScanTypeAliases(boolean autoScanTypeAliases) {
        this.autoScanTypeAliases = autoScanTypeAliases;
    }

    public void setBaseClass(Class<?> baseClass) {
        this.baseClass = baseClass;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
        SqlSessionFactory sqlSessionFactory = getObject();
        MybatisUtils.register(new MybatisDaoTemplate(sqlSessionFactory));
    }

    /**
     * Mybatis提供的类别名是扫描所有的Object，这里添加扫描逻辑
     */
    protected SqlSessionFactory buildSqlSessionFactory() throws IOException {
        scanTypeAliases();
        SqlSessionFactory sqlSessionFactory = super.buildSqlSessionFactory();
        Configuration config = sqlSessionFactory.getConfiguration();
        if (config instanceof com.forms.beneform4j.core.dao.mybatis.Configuration) {
            config = ((com.forms.beneform4j.core.dao.mybatis.Configuration) config).getDelegate();
        }
        setDefaultResultType(config, Map.class);
        CoreUtils.setProperty(config, "mapperRegistry", new MapperRegistry(config));
        return sqlSessionFactory;
    }

    /**
     * 在配置文件基础上自动扫描类型简称
     */
    private void scanTypeAliases() {
        if (this.autoScanTypeAliases && hasLength(this.typeAliasesScanPackage) && null != baseClass) {
            String[] typeAliasPackageArray = tokenizeToStringArray(this.typeAliasesScanPackage, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);
            List<Class<?>> list = new ArrayList<Class<?>>();
            List<String> alias = new ArrayList<String>();
            MetaObject meta = SystemMetaObject.forObject(this);
            Class<?>[] orig = (Class<?>[]) meta.getValue("typeAliases");
            if (null != orig) {
                for (Class<?> t : orig) {
                    list.add(t);
                    alias.add(t.getSimpleName().toLowerCase());
                }
            }
            for (String packageToScan : typeAliasPackageArray) {
                for (Class<?> type : CoreUtils.scanClassesByParentCls(packageToScan, baseClass)) {
                    String a = type.getSimpleName().toLowerCase();
                    if (!alias.contains(a)) {
                        list.add(type);
                        alias.add(a);
                    } else {
                        CommonLogger.warn("Mybatis在自动扫描注册别名时，发现有多个可简写为" + type.getSimpleName() + "的类，将取第一个类，忽略" + type);
                    }
                }
            }
            super.setTypeAliases(list.toArray(new Class<?>[list.size()]));
        }
    }

    /**
     * 设置默认的查询结果返回类型
     * 
     * @param configuration
     */
    private void setDefaultResultType(Configuration configuration, Class<?> cls) {
        try {
            Field resultMaps = MappedStatement.class.getDeclaredField("resultMaps");
            resultMaps.setAccessible(true);
            for (Iterator<MappedStatement> i = configuration.getMappedStatements().iterator(); i.hasNext();) {
                Object o = i.next();
                if (o instanceof MappedStatement) {
                    MappedStatement ms = (MappedStatement) o;
                    if (SqlCommandType.SELECT.equals(ms.getSqlCommandType()) && ms.getResultMaps().isEmpty()) {
                        ResultMap.Builder inlineResultMapBuilder = new ResultMap.Builder(configuration, ms.getId() + "-Inline", cls, new ArrayList<ResultMapping>(), null);
                        ResultMap resultMap = inlineResultMapBuilder.build();
                        List<ResultMap> rm = new ArrayList<ResultMap>();
                        rm.add(resultMap);
                        resultMaps.set(ms, Collections.unmodifiableList(rm));
                    } else {
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
