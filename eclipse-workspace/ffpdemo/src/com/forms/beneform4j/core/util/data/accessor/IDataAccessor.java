package com.forms.beneform4j.core.util.data.accessor;

import java.util.Iterator;
import java.util.Map;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 数据访问器<br>
 * Author : LinJisong <br>
 * Version : 1.0.1 <br>
 * Since : 1.0.1 <br>
 * Date : 2017-1-17<br>
 */
public interface IDataAccessor {

    /**
     * 获取简单对象属性值
     * 
     * @param property 属性/表达式
     * @return 属性/表达式值
     */
    public Object value(String property);

    /**
     * 获取简单对象属性值
     * 
     * @param property 属性/表达式
     * @param cls 期望的结果类型
     * @return 属性/表达式值
     */
    public <T> T value(String property, Class<T> cls);

    /**
     * 设置属性值
     * 
     * @param property 属性/表达式
     * @param value 属性/表达式值
     */
    public void set(String property, Object value);

    /**
     * 添加变量
     * 
     * @param key 变量名
     * @param value 变量值
     */
    public void addVar(String key, Object value);

    /**
     * 添加一组变量
     * 
     * @param vars 变量
     */
    public void addVars(Map<? extends String, ? extends Object> vars);

    /**
     * 获取迭代对象属性值
     * 
     * @param property 属性/表达式
     * @return 可以迭代的属性/表达式值
     */
    public Iterator<Object> iterator(String property);

    /**
     * 判断条件是否满足
     * 
     * @param condition 属性/表达式条件
     * @return 是否为true
     */
    public boolean match(String condition);

    /**
     * 获取派生数据访问器
     * 
     * @param property 属性/表达式
     * @return 子数据访问器
     */
    public IDataAccessor derive(String property);

    /**
     * 获取父数据访问器
     * 
     * @return 父数据访问器
     */
    public IDataAccessor getParent();

    /**
     * 获取Root对象
     * 
     * @return Root对象
     */
    public Object getRoot();
}
