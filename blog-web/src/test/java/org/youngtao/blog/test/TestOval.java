package org.youngtao.blog.test;

import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.Min;

public class TestOval {
	
	@Length(min = 6, max = 10,errorCode="110",message="{min}错了{max}")
	private String name;

	public static void main(String[] args) {
		TestOval ovalTest = new TestOval();
		ovalTest.name = "kolor";

		Validator validator = new Validator();

		List<ConstraintViolation> ret = validator.validate(ovalTest);
		for (int i = 0; i < ret.size(); i++) {
			System.out.println(ret.get(i).getErrorCode());
			System.out.println(ret.get(i).getMessage());
		}
	}
}
