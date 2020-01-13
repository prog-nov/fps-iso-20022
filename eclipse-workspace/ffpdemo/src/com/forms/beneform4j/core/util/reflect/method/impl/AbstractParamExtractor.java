package com.forms.beneform4j.core.util.reflect.method.impl;

import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.reflect.method.IParamExtractor;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 抽象的参数提取器实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-27<br>
 */
public abstract class AbstractParamExtractor implements IParamExtractor {

    /**
     * 转换为期望的目标类型
     */
    @Override
    public <E> E extract(Class<E> type, String expression, Object args) {
        Object param = this.doExtract(type, expression, args);
        if (null != param) {
            if (type.isAssignableFrom(param.getClass())) {
                return type.cast(param);
            } else {
                return CoreUtils.convert(param, type);
            }
        }
        return null;
    }

    /**
     * 执行真正的提取操作
     * 
     * @param type 期望的目标类型
     * @param expression 提取的表达式
     * @param args 参数对象
     * @return 提取的参数值
     */
    abstract protected Object doExtract(Class<?> type, String expression, Object args);
}
