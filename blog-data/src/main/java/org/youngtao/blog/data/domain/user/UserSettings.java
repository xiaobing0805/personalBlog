package org.youngtao.blog.data.domain.user;

import java.io.Serializable;

import org.youngtao.blog.data.domain.BaseDomain;

public class UserSettings extends BaseDomain implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键，自增
	 */
	private Integer id;
	
	/**
	 * 用户外键
	 */
	private String userId;
	
	/**
	 * 键
	 */
	private String key;
	
	/**
	 * 值
	 */
	private String value;
	
	/**
	 * 备注
	 */
	private String remarks;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}
