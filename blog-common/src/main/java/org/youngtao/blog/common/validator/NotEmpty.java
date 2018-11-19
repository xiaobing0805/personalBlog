package org.youngtao.blog.common.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.youngtao.blog.common.validator.impl.NotEmptyValidator;

@Retention(RetentionPolicy.RUNTIME)  
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER })  
@Constraint(validatedBy = { NotEmptyValidator.class })
public @interface NotEmpty {

	String field() default "";  
	
	String errorCode() default "";  
	  
    String errorMessage() default "";  
  
    Class<?>[] groups() default {};  
  
    Class<? extends Payload>[] payload() default {};
}
