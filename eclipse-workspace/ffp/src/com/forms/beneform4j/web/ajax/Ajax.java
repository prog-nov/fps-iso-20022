package com.forms.beneform4j.web.ajax;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 标识Ajax方法的注解<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2013-10-6<br>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Ajax {
    /**
     * 唯一标识该方法
     * 
     * @return
     */
    String id();

    /**
     * 描述方法参数与Request请求参数对应关系 格式：{"0=name","1=code"} 此时按标示顺序和方法参数对应 或 {"name","code"}
     * 此时按出现顺序和方法参数对应，但优先级低于第一种方式
     * 
     * @return
     */
    String[] args() default {};

    /**
     * 优先级，用于处理冲突
     * 
     * @return
     */
    int order() default 0;
}
