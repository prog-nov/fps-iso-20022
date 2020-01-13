package com.forms.beneform4j.core.util.clean;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 平台清理任务的注解<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-19<br>
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Clean {

    /**
     * corn表达式
     * 
     * @return corn表达式
     */
    String cron() default "";

    /**
     * 清理任务描述
     * 
     * @return 清理任务描述
     */
    String description() default "";
}
