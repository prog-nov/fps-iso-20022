package com.forms.ffp.msg.iclfps.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ java.lang.annotation.ElementType.FIELD })
public @interface FFPValidatePattern
{
	String[]value() default { "" };

	String[]pattern() default { "" };

	String[]patternKey() default { "" };
}
