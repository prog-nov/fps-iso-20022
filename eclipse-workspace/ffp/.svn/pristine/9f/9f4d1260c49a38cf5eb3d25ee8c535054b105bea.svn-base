package com.forms.beneform4j.core.dao.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 批量执行时的批量SqlId注解，用于动态代理批量执行<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23 <br>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SqlRefs {

    /**
     * 批处理的SqlId组
     * <p>
     * 注意：使用该注解时，要求@SqlRef两个或两个以上，如果只有一个@SqlRef，请在方法上直接使用@SqlRef
     * </p>
     * 
     * @return {@link SqlRef}组，表示要执行的一组sqlId
     */
    SqlRef[] value();
}
