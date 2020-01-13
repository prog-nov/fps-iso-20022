package com.forms.beneform4j.core.util.stack;

import java.io.Serializable;

import org.slf4j.Logger;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 堆栈信息接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public interface IStack extends Serializable {

    /**
     * 获取当前环境的跟踪ID
     * 
     * @return 跟踪ID
     */
    public String getTrackId();

    /**
     * 获取日志类
     * 
     * @return 日志类
     */
    public Logger getLogger();

    /**
     * 获取堆栈
     * 
     * @return 堆栈
     */
    public StackTraceElement getStack();

    /**
     * 获取描述信息
     * 
     * @return 描述信息
     */
    public String getMessage();

    /**
     * 获取异常，如果没有异常，返回null
     * 
     * @return 异常
     */
    public Throwable getThrowable();

}
