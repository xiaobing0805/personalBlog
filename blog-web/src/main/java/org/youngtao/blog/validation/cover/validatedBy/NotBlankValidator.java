package org.youngtao.blog.validation.cover.validatedBy;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.youngtao.blog.validation.cover.NotBlank;

public class NotBlankValidator implements ConstraintValidator<NotBlank, CharSequence> {

	@Override
	public void initialize(NotBlank constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
		if (value == null) {
			
			return true;
		}

		return value.toString().trim().length() > 0;
	}

}
