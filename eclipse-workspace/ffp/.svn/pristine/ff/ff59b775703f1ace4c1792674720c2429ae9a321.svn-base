package com.forms.beneform4j.core.util.exception.meta;

import java.util.List;

import com.forms.beneform4j.core.util.exception.handler.IExceptionHandler;
import com.forms.beneform4j.core.util.exception.level.ExceptionLevel;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 异常元信息接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public interface IExceptionMeta {

    /**
     * 父代码
     * 
     * @return 异常父代码
     */
    public String getParentCode();

    /**
     * 异常代码
     * 
     * @return 异常代码
     */
    public String getCode();

    /**
     * 国际化描述信息的Key值
     * 
     * @return 国际化Key值
     */
    public String getMessageKey();

    /**
     * 获取异常级别
     * 
     * @return 异常级别
     */
    public ExceptionLevel getLevel();

    /**
     * 异常返回逻辑视图
     * 
     * @return 异常视图
     */
    public String getView();

    /**
     * 异常处理器列表
     * 
     * @return 处理器列表
     */
    public List<IExceptionHandler> getHandlers();

    /**
     * 获取可能导致该异常的原始异常列表
     * 
     * @return 可能的原始异常列表
     */
    public List<Class<? extends Throwable>> getCauses();
}
