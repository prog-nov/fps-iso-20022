package com.forms.ffp.msg.iclfps.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ java.lang.annotation.ElementType.FIELD })
public @interface FFPValidateLength
{
	String[]value() default { "" };

	boolean inclusive() default true;

	int minLength() default 0;

	String minLengthKey() default "";

	int maxLength() default 1024;

	String maxLengthKey() default "";
}
