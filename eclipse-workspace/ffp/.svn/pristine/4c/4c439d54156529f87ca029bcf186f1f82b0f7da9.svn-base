package com.forms.beneform4j.core.dao.sql.function;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.forms.beneform4j.core.dao.jndi.IJndi;
import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.config.BaseConfig;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : SQL配置函数<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-10-26<br>
 */
public interface ISqlConfigFunction {

    /**
     * 优先级，如果有多个同名函数，使用order值小的
     * 
     * @return
     */
    public int getOrder();

    /**
     * 是否单例
     * 
     * @return
     */
    public boolean isSingleon();

    /**
     * 函数名称
     * 
     * @return
     */
    public String getName();

    /**
     * 在设置参数值的时候，解析为值，用于JDBC中的PreparedStatement.setObject()/setString()/setDouble()...等方法
     * 
     * @param jndi 数据源
     * @param parameter 传入Mybatis的参数对象
     * @param expression SqlMapper配置中的参数表达式
     * @param args SqlMapper配置中的参数表达式
     * @return 需要设置的参数值
     */
    public Object evaluateValue(IJndi jndi, Object parameter, String expression, String[] args);

    /**
     * 在替换SQL语句的时候，解析为SQL字符串
     * 
     * @param jndi 数据源
     * @param parameter 传入Mybatis的参数对象
     * @param expression SqlMapper配置中的参数表达式
     * @param args SqlMapper配置中的参数表达式
     * @return 需要替换的SQL字符串
     */
    public String evaluateSql(IJndi jndi, Object parameter, String expression, String[] args);

    /**
     * SQL配置函数管理类
     */
    public class Manager {

        private static Map<String, ISqlConfigFunctionBuilder> sqlTextFunctionBuilders;

        public static ISqlConfigFunction getSqlTextFunction(String name) {
            if (sqlTextFunctionBuilders == null) {
                synchronized (Manager.class) {
                    if (sqlTextFunctionBuilders == null) {
                        sqlTextFunctionBuilders = new HashMap<String, ISqlConfigFunctionBuilder>();
                        Map<String, Integer> orders = new HashMap<String, Integer>();

                        Set<Class<? extends ISqlConfigFunction>> clss = CoreUtils.scanClassesByParentCls(BaseConfig.getScanPackage(), ISqlConfigFunction.class);
                        if (clss != null) {
                            for (final Class<? extends ISqlConfigFunction> cls : clss) {
                                ISqlConfigFunction fn = CoreUtils.newInstance(cls);
                                String fname = fn.getName();
                                Integer order = orders.get(fname);
                                if (null == order || fn.getOrder() < order) {// 不存在优先级更高的函数
                                    orders.put(fname, fn.getOrder());
                                    if (fn.isSingleon()) {
                                        sqlTextFunctionBuilders.put(fname, new SingleonSqlConfigFunctionBuilder(fn));
                                    } else {
                                        sqlTextFunctionBuilders.put(fname, new ClassSqlConfigFunctionBuilder(cls));
                                    }
                                }
                            }
                        }

                        Set<Class<? extends ISqlConfigFunctionFactory>> factoryClss = CoreUtils.scanClassesByParentCls(BaseConfig.getScanPackage(), ISqlConfigFunctionFactory.class);
                        if (null != factoryClss) {
                            for (Class<? extends ISqlConfigFunctionFactory> cls : factoryClss) {
                                final ISqlConfigFunctionFactory factory = CoreUtils.newInstance(cls);
                                Set<ISqlConfigFunction> fns = factory.getAllSqlConfigFunctions();
                                if (null != fns) {
                                    for (ISqlConfigFunction fn : fns) {
                                        final String fname = fn.getName();
                                        Integer order = orders.get(fname);
                                        if (null == order || fn.getOrder() < order) {// 不存在优先级更高的函数
                                            orders.put(fname, fn.getOrder());
                                            if (fn.isSingleon()) {
                                                sqlTextFunctionBuilders.put(fname, new SingleonSqlConfigFunctionBuilder(fn));
                                            } else {
                                                sqlTextFunctionBuilders.put(fname, new FactorySqlConfigFunctionBuilder(factory, fname));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if (sqlTextFunctionBuilders.containsKey(name)) {
                return sqlTextFunctionBuilders.get(name).builder();
            } else {
                return null;
            }
        }

        private interface ISqlConfigFunctionBuilder {
            public ISqlConfigFunction builder();
        }

        private static class SingleonSqlConfigFunctionBuilder implements ISqlConfigFunctionBuilder {
            private ISqlConfigFunction instance;

            private SingleonSqlConfigFunctionBuilder(ISqlConfigFunction instance) {
                this.instance = instance;
            }

            public ISqlConfigFunction builder() {
                return instance;
            }
        }
        private static class ClassSqlConfigFunctionBuilder implements ISqlConfigFunctionBuilder {
            private Class<? extends ISqlConfigFunction> cls;

            private ClassSqlConfigFunctionBuilder(Class<? extends ISqlConfigFunction> cls) {
                this.cls = cls;
            }

            public ISqlConfigFunction builder() {
                return CoreUtils.newInstance(cls);
            }
        }
        private static class FactorySqlConfigFunctionBuilder implements ISqlConfigFunctionBuilder {
            private ISqlConfigFunctionFactory factory;
            private String name;

            private FactorySqlConfigFunctionBuilder(ISqlConfigFunctionFactory factory, String name) {
                this.factory = factory;
                this.name = name;
            }

            public ISqlConfigFunction builder() {
                return factory.getSqlConfigFunction(name);
            }
        }
    }
}
