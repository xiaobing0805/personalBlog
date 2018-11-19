package org.youngtao.blog.common.validator.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotNull;

public class NotEmptyValidator implements ConstraintValidator<NotNull, Object> {

  
    @Override  
    public boolean isValid(Object str, ConstraintValidatorContext constraintValidatorContext) {  
        return str != null;  
    }

	@Override
	public void initialize(NotNull annotation) {
		
	}
}
