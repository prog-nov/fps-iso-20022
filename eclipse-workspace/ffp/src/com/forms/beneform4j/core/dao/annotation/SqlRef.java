package com.forms.beneform4j.core.dao.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : SQL-ID重定向注解 <br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SqlRef {
    /**
     * 标识使用哪个SqlId
     * 
     * @return sqlId值
     */
    String value() default "";

    /**
     * 是否为相对于当前类的路径
     * 
     * @return 是否相对于当前类路径，当前类是指标示该注解方法所在的类
     */
    boolean classpath() default true;
}
