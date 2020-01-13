package com.forms.beneform4j.core.dao.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 执行注解，用于动态代理批量执行，不能单独使用，需配置@Executes一起使用<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-13 <br>
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Execute {

    /**
     * SqlRef引用
     * 
     * @return SqlRef引用
     */
    SqlRef sqlRef();

    /**
     * 批量参数
     * 
     * @return 批量参数
     */
    BatchParam param();

    /**
     * 执行的条件
     * 
     * @return 执行的条件
     */
    String condition() default "";
}
