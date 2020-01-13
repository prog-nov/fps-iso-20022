package com.forms.beneform4j.core.util.exception.level;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 异常级别<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public enum ExceptionLevel {
    /**
     * 业务异常，只需要将异常信息提示给业务人员即可，一般由数据校验组件或者开发人员抛出
     */
    INFO,
    /**
     * 运行时异常，影响本模块，或者局部非重点功能，一般需要技术人员跟踪，在处理该级别异常时，要求同时打印异常堆栈信息，写好日志记录
     */
    ERROR,
    /**
     * 严重异常，影响整个系统正常运行或者重要模块功能，不可控的系统错误导致的异常，会导致系统无法正常运行
     */
    FATAL;

    /**
     * 根据名称获取异常级别实例，默认为FATAL
     * 
     * @param name 级别名称
     * @return 异常级别
     */
    public static ExceptionLevel instance(String name) {
        for (ExceptionLevel el : values()) {
            if (el.name().equalsIgnoreCase(name)) {
                return el;
            }
        }
        return FATAL;
    }

    /**
     * 获取异常级别的描述
     * 
     * @return 异常级别的描述
     */
    public String getDescription() {
        return "Exception-Level:" + this.name();
    }
}
