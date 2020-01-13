package com.forms.beneform4j.core.dao.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 批量执行时的批量参数注解，用于动态代理批量执行<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23 <br>
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BatchParam {

    /**
     * 是否为批量
     * 
     * @return 是否为批量
     */
    boolean value() default true;

    /**
     * 批量参数中每一项存入map结构时的名称
     * 
     * @return 数据项名称
     */
    String item() default "item";

    /**
     * 表示批量参数的属性
     * 
     * @return 批量参数属性
     */
    String property() default "this";

    /**
     * 当前索引存入map结构时的名称
     * 
     * @return 索引名称
     */
    String index() default "index";
}
