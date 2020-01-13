package com.forms.beneform4j.core.util.exception.meta;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 异常元信息加载器接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public interface IExceptionMetaLoader {

    /**
     * 查找异常配置元信息
     * 
     * @param code 异常代码
     * @param cause 异常原因
     * @return 异常元信息
     */
    public IExceptionMeta lookup(String code, Throwable cause);
}
