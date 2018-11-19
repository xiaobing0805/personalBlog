package org.youngtao.blog.common.domain;

import java.io.Serializable;

public class Result<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 操作码
	 */
	private Boolean successFul = true;

	/**
	 * 错误码
	 */
	private String errorCode;
	
	/**
	 * 错误信息
	 */
	private String errorMessage;
	
	/**
	 * 时间戳
	 */
	private String timestamp;
	
	/**
	 * 数据
	 */
	private T data;

	public Boolean isSuccessFul() {
		return successFul;
	}

	public void setSuccessFul(Boolean successFul) {
		this.successFul = successFul;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	
}
