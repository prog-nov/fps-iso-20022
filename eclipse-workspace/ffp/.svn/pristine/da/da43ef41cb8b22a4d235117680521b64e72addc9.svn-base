package com.forms.beneform4j.core.dao.mybatis.interceptor;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.Properties;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : Mybatis拦截器插件抽象实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24 <br>
 */
public abstract class AbstractInterceptor implements Interceptor {

    private Properties properties;

    private static Field target;
    static {
        try {
            target = Plugin.class.getDeclaredField("target");
            target.setAccessible(true);
        } catch (Exception e) {
        }
    }

    /**
     * 获取拦截的目标类
     * 
     * @param invocation
     * @param cls 目标类型
     * @return 目标类
     */
    protected <T> T getTarget(Invocation invocation, Class<T> cls) {
        Object obj = invocation.getTarget();
        while (Proxy.isProxyClass(obj.getClass())) {// 处理嵌套插件的目标类获取
            try {
                obj = target.get(Proxy.getInvocationHandler(obj));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cls.cast(obj);
    }

    /**
     * 获取拦截的目标方法的第index个参数
     * 
     * @param invocation
     * @param cls 参数类型
     * @param index 参数索引
     * @return 第index个参数对象
     */
    protected <T> T getArgument(Invocation invocation, Class<T> cls, int index) {
        Object obj = invocation.getArgs()[index];
        return cls.cast(obj);
    }

    /**
     * 获取配置的属性
     * 
     * @param key 属性名
     * @return 属性值
     */
    protected String getProperty(String key) {
        return null == properties ? null : properties.getProperty(key);
    }

    /**
     * 包装插件
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * 设置插件属性
     */
    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

}
