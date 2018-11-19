package org.youngtao.blog.common.domain;

import java.io.Serializable;

/**
 * 通用基础接口类
 * @author linyantao
 *
 */
public class IRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 默认中文（简体）
	 */
	private String language = "zh_CN";

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
}
