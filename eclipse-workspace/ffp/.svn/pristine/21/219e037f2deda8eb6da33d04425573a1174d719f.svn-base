package com.forms.beneform4j.web.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.web.bind.annotation.ResponseBody;

import com.forms.beneform4j.util.json.serial.converter.IJsonConverter;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : JsonField注解<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-20<br>
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ResponseBody
public @interface JsonField {

    /**
     * 属性名称
     * 
     * @return
     */
    String value();

    /**
     * 属性别名
     * 
     * @return
     */
    String alias() default "";

    /**
     * 是否忽略
     * 
     * @return
     */
    boolean ignore() default false;

    /**
     * 转换类的Bean名称
     * 
     * @return
     */
    String convertBean() default "";

    /**
     * 转换类的实现类，优先级低于convertBean
     * 
     * @return
     */
    Class<? extends IJsonConverter> convertCls() default DefaultJsonConverter.class;

    public class DefaultJsonConverter implements IJsonConverter {
        @Override
        public Object convert(Object container, Object property, Object value) {
            throw new UnsupportedOperationException("DefaultJsonConverter only used to marked the default value");
        }
    }
}
