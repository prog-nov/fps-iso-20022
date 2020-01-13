package com.forms.beneform4j.core.dao.sql.interceptor;

import com.forms.beneform4j.core.dao.jndi.IJndi;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : SQL语句拦截器接口，用于替换SQL片段<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24<br>
 */
public interface ISqlInterceptor {

    /**
     * 执行SQL拦截
     * 
     * @param jndi 数据源
     * @param src 原SQL
     * @param root 参数对象
     * @return 拦截后的SQL
     */
    public String intercept(IJndi jndi, String src, Object root);
}
