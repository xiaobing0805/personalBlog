package org.youngtao.blog.data.domain.user;

import java.io.Serializable;

import org.youngtao.blog.data.domain.BaseDomain;

public class User extends BaseDomain implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 
	/**
	 * 随机32位
	 */
	private String id;
	 
	/**
	 * 登录名
	 */
	private String  loginName;
	 
	/**
	 * 密码
	 */
	private String password;
	 
	/**
	 * 盐（随机数）
	 */
	private String salt;
	 
	/**
	 * 邮箱
	 */
	private String email;
	 
	/**
	 * 名称
	 */
	private String name;
	 
	/**
	 * 头像URL
	 */
	private String portraitUrl;
	 
	/**
	 * 是否禁用（0：正常、1：禁用）
	 */
	private Integer isDisable;
	
	/**
	 * 用户会话ID
	 */
	private String sessionId;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPortraitUrl() {
		return portraitUrl;
	}

	public void setPortraitUrl(String portraitUrl) {
		this.portraitUrl = portraitUrl;
	}

	public Integer getIsDisable() {
		return isDisable;
	}

	public void setIsDisable(Integer isDisable) {
		this.isDisable = isDisable;
	}
}
