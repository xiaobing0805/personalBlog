package org.youngtao.blog.controller.request.user;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.util.StringUtils;
import org.youngtao.blog.common.constant.Constants;
import org.youngtao.blog.controller.request.PageRequest;
import org.youngtao.blog.validation.ErrorMsg;
import org.youngtao.blog.validation.Valid;

public class UserRequest extends PageRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 
	/**
	 * 随机32位
	 */
	@NotBlank(groups = {Valid.update.class},message = ErrorMsg.PARAMS_NO_NULL)
	private String id;
	 
	/**
	 * 登录名
	 */
	@NotBlank(groups = {Valid.add.class},message = ErrorMsg.PARAMS_NO_NULL)
	private String  loginName;
	 
	/**
	 * 密码
	 */
	@NotBlank(groups = {Valid.add.class,Valid.updatePwd.class},message = ErrorMsg.PARAMS_NO_NULL)
	private String password;
	
	@NotBlank(groups = {Valid.add.class,Valid.updatePwd.class},message = ErrorMsg.PARAMS_NO_NULL)
	private String confirmPassword;
	 
	/**
	 * 盐（随机数）
	 */
	private String salt;
	 
	/**
	 * 邮箱
	 */
	@NotBlank(groups = {Valid.add.class,Valid.update.class},message = ErrorMsg.PARAMS_NO_NULL)
	private String email;
	 
	/**
	 * 名称
	 */
	@NotBlank(groups = {Valid.add.class,Valid.update.class},message = ErrorMsg.PARAMS_NO_NULL)
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
	 * 是否超管（0：否，1：是）
	 */
	private Integer isSupper;
	
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

	public Integer getIsSupper() {
		return isSupper;
	}

	public void setIsSupper(Integer isSupper) {
		this.isSupper = isSupper;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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
		if(StringUtils.isEmpty(portraitUrl)){
			portraitUrl = Constants.DEFAULT_PORTRAIT;
		}
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
