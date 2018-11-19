package org.youngtao.blog.data.domain.user;

import java.io.Serializable;

import org.youngtao.blog.data.domain.BaseDomain;

public class RecordAccess extends BaseDomain implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 自增
	 */
	private Integer id;
	
	/**
	 * 用户会话ID
	 */
	private String sessionId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
}
