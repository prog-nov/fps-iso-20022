package com.forms.beneform4j.core.dao.call;

import java.util.Iterator;

import com.forms.beneform4j.core.util.exception.Beneform4jRuntimeException;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 调用存储过程的返回结果<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23 <br>
 */
public interface ICallResult {

    /**
     * 根据参数名称返回输出参数
     * 
     * @param name 输出参数名称
     * @return 和输出参数名称相对应的返回结果，如果不存在输出参数，抛出平台运行时异常{@link Beneform4jRuntimeException}
     */
    public <T> T getOutputParam(String name);

    /**
     * 返回输出参数名称的迭代器
     * 
     * @return 输出参数名迭代器
     */
    public Iterator<String> iterator();
}
