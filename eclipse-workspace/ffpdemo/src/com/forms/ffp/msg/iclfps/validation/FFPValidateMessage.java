package com.forms.ffp.msg.iclfps.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.FIELD})
public @interface FFPValidateMessage
{
  String value() default "";
  
  String field() default "";
  
  String message() default "";
  
  String returnCode() default "";
}
