package org.youngtao.blog.data.domain;

import java.io.Serializable;
import java.util.Date;

public class BaseDomain implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 创建者
	 */
	private String creator;
	
	/**
	 * 操作者
	 */
	private String operator;
	
	/**
	 * 创建时间
	 */
	private Date createdTime;
	
	/**
	 * 修改时间
	 */
	private Date modifedTime;

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getModifedTime() {
		return modifedTime;
	}

	public void setModifedTime(Date modifedTime) {
		this.modifedTime = modifedTime;
	}
}
