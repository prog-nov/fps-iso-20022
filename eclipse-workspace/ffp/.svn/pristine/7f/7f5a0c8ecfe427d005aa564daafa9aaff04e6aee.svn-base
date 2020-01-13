package com.forms.beneform4j.security.core.common.info;

import java.util.Iterator;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 安全服务返回信息接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-8<br>
 */
public interface ISecurityInfo {

    /**
     * 获取返回代码
     * 
     * @return 返回代码
     */
    public String getCode();

    /**
     * 设置返回代码
     * 
     * @param code 返回代码
     */
    public void setCode(String code);

    /**
     * 获取返回描述
     * 
     * @return 返回描述
     */
    public String getMessage();

    /**
     * 设置返回描述
     * 
     * @param message 返回描述
     */
    public void setMessage(String message);

    /**
     * 是否成功
     * 
     * @return 是否成功
     */
    public boolean isSuccess();

    /**
     * 设置状态
     * 
     * @success 是否成功状态
     */
    public void setSuccess(boolean success);

    /**
     * 设置属性
     * 
     * @param name 属性名称
     * @param value 属性值
     */
    public void setProperty(String name, Object value);

    /**
     * 获取属性
     * 
     * @param name 属性名称
     * @return 属性值
     */
    public Object getProperty(String name);

    /**
     * 根据目标类型获取属性
     * 
     * @param name 属性名称
     * @param cls 目标类型
     * @return 属性值
     */
    public <E> E getProperty(String name, Class<E> cls);

    /**
     * 获取属性名称迭代器
     * 
     * @return 属性名称迭代器
     */
    public Iterator<String> getPeropertyNames();
}
