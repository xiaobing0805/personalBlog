package org.youngtao.blog.validation;

public interface ErrorMsg {

	/**
	 * 参数不能为空
	 */
	String PARAMS_NO_NULL = "{params.not.blank}";
	
	/**
	 * 参数不能小于某个值
	 */
	String PARAMS_NOT_LESS_ONE = "{params.not.less.one}";
}
