package com.forms.beneform4j.web.servlet;

import com.forms.beneform4j.core.dao.jndi.IJndi;
import com.forms.beneform4j.core.dao.sql.resolver.impl.AbstractSqlResolver;
import com.forms.beneform4j.core.util.CoreUtils;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : Servlet上下文环境参数解析器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-11<br>
 */
public class ServletContextSqlResolver extends AbstractSqlResolver {

    @Override
    public boolean isSupport(IJndi jndi, String expression) {
        return null != expression && (expression.startsWith("request.") || expression.startsWith("session."));
    }

    @Override
    public Object resolver(IJndi jndi, Object parameterObject, String expression) {
        String exp = "";
        Object root = null;
        if (expression.startsWith("request.params.")) {// 请求参数直接返回
            exp = expression.substring(15);
            return ServletHelp.getRequest().getParameter(exp);
        } else if (expression.startsWith("request.")) {// 请求属性
            exp = expression.substring(8);
            root = ServletHelp.getRequest().getAttribute(exp);
        } else if (expression.startsWith("session.")) {// 会话属性
            exp = expression.substring(8);
            root = ServletHelp.getSession().getAttribute(exp);
        }

        if (null == root) {
            return null;
        } else {
            int index = exp.indexOf('.');
            if (-1 == index) {
                return root;
            } else {
                return CoreUtils.getProperty(root, exp.substring(index + 1));
            }
        }
    }

}
