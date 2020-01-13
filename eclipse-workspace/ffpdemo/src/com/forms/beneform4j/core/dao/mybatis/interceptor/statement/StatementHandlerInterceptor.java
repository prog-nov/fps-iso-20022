package com.forms.beneform4j.core.dao.mybatis.interceptor.statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.RowBounds;

import com.forms.beneform4j.core.dao.dialect.IDialect;
import com.forms.beneform4j.core.dao.dialect.impl.SybaseASE;
import com.forms.beneform4j.core.dao.exception.DaoExceptionCodes;
import com.forms.beneform4j.core.dao.jndi.IJndi;
import com.forms.beneform4j.core.dao.jndi.JndiManager;
import com.forms.beneform4j.core.dao.mybatis.MybatisUtils;
import com.forms.beneform4j.core.dao.mybatis.interceptor.AbstractInterceptor;
import com.forms.beneform4j.core.dao.mybatis.page.PageAdapter;
import com.forms.beneform4j.core.dao.mybatis.parameter.ExpressionParameterHandler;
import com.forms.beneform4j.core.dao.sql.SqlManager;
import com.forms.beneform4j.core.dao.sql.interceptor.ISqlInterceptor;
import com.forms.beneform4j.core.dao.sql.resolver.ISqlResolver;
import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.core.util.page.IPage;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 语句预处理的拦截器插件<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24 <br>
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})
        // @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class,
        // Integer.class })
})
public class StatementHandlerInterceptor extends AbstractInterceptor {

    /**
     * 执行拦截器逻辑：
     * <p>
     * <ul>
     * <li>1.格式化字符串，去掉首尾空白字符，压缩中间连续多个空白字符为一个空白字符
     * <li>2.执行SQL语句块的动态替换，使用平台配置的SQL拦截器{@link ISqlInterceptor}进行SQL语句块的替换
     * <li>3.修改参数处理器为可执行参数解析器{@link ISqlResolver}的处理器，实现参数设置前的解析
     * <li>4.如果是分页查询，根据需要计算总记录数，并替换为查询区间段的记录的SQL语句，实现物理分页
     * </ul>
     * <p>
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler handler = super.getTarget(invocation, StatementHandler.class);
        if (handler instanceof RoutingStatementHandler) {
            handler = (StatementHandler) CoreUtils.getProperty(handler, "delegate");
        }
        MetaObject meta = SystemMetaObject.forObject(handler);

        // 拦截SQL：SQL执行参数替换,SQL语句静态替换
        BoundSql boundSql = handler.getBoundSql();
        Object parameterObject = boundSql.getParameterObject();
        MappedStatement mappedStatement = null;
        ParameterHandler ph = handler.getParameterHandler();
        if (!(ph instanceof ExpressionParameterHandler)) {
            mappedStatement = (MappedStatement) meta.getValue("mappedStatement");
            ph = new ExpressionParameterHandler(mappedStatement, parameterObject, boundSql);
            meta.setValue("parameterHandler", ph);
        } else {
            mappedStatement = ((ExpressionParameterHandler) ph).getMappedStatement();
        }

        String sqlId = mappedStatement.getId();
        IJndi jndi = JndiManager.getJndi(MybatisUtils.getDataSource(sqlId));
        String sql = boundSql.getSql();
        sql = CoreUtils.formatWhitespace(sql);// 格式化SQL字符串
        sql = SqlManager.doIntercept(jndi, sql, parameterObject);
        meta.setValue("boundSql.sql", sql);

        // 分页
        RowBounds bounds = (RowBounds) meta.getValue("rowBounds");
        if (bounds instanceof PageAdapter) {
            PageAdapter pa = (PageAdapter) bounds;
            IPage page = null;
            if (null != pa.getPage()) {
                page = pa.getPage();
                Connection conn = (Connection) invocation.getArgs()[0];
                IDialect dialect = JndiManager.getDialect(conn);// 数据库方言
                setPageProperties(page, mappedStatement.getStatementLog(), conn, ph, dialect, sql);
                // 分页SQL
                String pageSql = dialect.getScopeSql(sql, page.getStart(), page.getPageSize());
                meta.setValue("rowBounds", RowBounds.DEFAULT);// 替换之前的指标
                meta.setValue("boundSql.sql", pageSql);
                // 特殊处理
                if (dialect instanceof SybaseASE) {
                    // 一般地，分页查询不处于一个事务中，但由于Spring的注解事务，会处于事务中，这里先结束事务，以便可以执行分页查询SQL
                    sybaseAse(conn);
                }
            }
        }
        return invocation.proceed();
    }

    /**
     * 设置分页属性
     * 
     * @param page 分页对象
     * @param log 日志对象
     * @param conn 数据库
     * @param handler 参数处理器
     * @param dialect 数据库方言
     * @param sql 原始SQL
     */
    private void setPageProperties(IPage page, Log log, Connection conn, ParameterHandler handler, IDialect dialect, String sql) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            long total = page.getTotalRecords();
            if (page.isNeedCalTotal()) {
                pstmt = conn.prepareStatement(dialect.getTotalSql(sql));
                handler.setParameters(pstmt);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    total = rs.getInt(1);
                }
                if (null != log) {
                    log.debug("<==      Total:(Page) calculate page totals [" + total + "]");
                }
            } else if (null != log) {
                log.debug("<==      Total:(Page) use old page totals [" + total + "]");
            }
            page.setPageProperty(total);
        } catch (SQLException e) {
            Throw.throwRuntimeException(DaoExceptionCodes.BF020005, e);
        } finally {
            try {
                if (null != rs) {
                    rs.close();
                }
            } catch (Exception e) {
            }
            try {
                if (null != pstmt) {
                    pstmt.close();
                }
            } catch (Exception e) {
            }
        }
    }

    private void sybaseAse(Connection conn) {
        try {
            conn.commit();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            // ignore
        }
    }
}
