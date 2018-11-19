package org.youngtao.blog.test;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.constraints.Length;

public class TestHibernate {

	@Length(min = 6, max = 10, message = "{min}{params.not.enough.in.length}{max}")
	private String name;

	public static void main(String[] args) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();  
        Validator validator = factory.getValidator();  
  
        TestHibernate entity = new TestHibernate();  
        entity.name = "sa";
        Set<ConstraintViolation<TestHibernate>> constraintViolations = validator.validate(entity);  
        for (ConstraintViolation<TestHibernate> constraintViolation : constraintViolations) {  
            System.out.println("对象属性:"+constraintViolation.getPropertyPath());  
            System.out.println("国际化key:"+constraintViolation.getMessageTemplate());  
            System.out.println("错误信息:"+constraintViolation.getMessage());  
        }  
	}
}
