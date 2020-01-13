package com.forms.beneform4j.core.dao.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 流式查询的每次获取记录条数，用于动态代理流式查询<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23 <br>
 */
@Target({ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FetchSize {

    /**
     * 每次获取的记录条数
     * <p>
     * 注意：每次获取的记录条数数值范围为 (0, 5000]，小于或等于0表示使用默认值
     * </p>
     * 
     * @return 每次读取记录数大小
     */
    int value() default -1;
}
