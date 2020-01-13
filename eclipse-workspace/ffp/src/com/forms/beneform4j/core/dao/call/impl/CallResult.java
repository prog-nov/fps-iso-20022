package com.forms.beneform4j.core.dao.call.impl;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.forms.beneform4j.core.dao.call.ICallResult;
import com.forms.beneform4j.core.dao.exception.DaoExceptionCodes;
import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.exception.Throw;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 调用存储过程的返回结果<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23 <br>
 */
public class CallResult implements ICallResult {

    private final Map<String, Object> resultMaps = new LinkedHashMap<String, Object>();

    /**
     * 添加一个返回结果
     * 
     * @param name 参数名称
     * @param result 返回结果
     */
    public void addResult(String name, Object result) {
        resultMaps.put(name, result);
    }

    /**
     * 添加一组返回结果
     * 
     * @param results 一组返回结果
     */
    public void addAllResult(Map<String, Object> results) {
        resultMaps.putAll(results);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T getOutputParam(String name) {
        if (!resultMaps.containsKey(name)) {
            Throw.createRuntimeException(DaoExceptionCodes.BF020001, name);
        }
        return CoreUtils.cast(resultMaps.get(name));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<String> iterator() {
        return resultMaps.keySet().iterator();
    }
}
