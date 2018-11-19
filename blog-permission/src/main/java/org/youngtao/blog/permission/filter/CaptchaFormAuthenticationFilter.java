package org.youngtao.blog.permission.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.youngtao.blog.common.constant.Constants;
import org.youngtao.blog.permission.captcha.CaptchaUsernamePasswordToken;
import org.youngtao.blog.permission.exception.CaptchaConnotBeEmptyException;
import org.youngtao.blog.permission.exception.PasswordConnotBeEmptyException;
import org.youngtao.blog.permission.exception.UsernameConnotBeEmptyException;

import com.alibaba.dubbo.common.utils.StringUtils;

public class CaptchaFormAuthenticationFilter extends FormAuthenticationFilter {

	private static final Logger LOG = LoggerFactory.getLogger(CaptchaFormAuthenticationFilter.class); 
	
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		if (isLoginSubmission(request, response)) {
            
		    Subject subject = getSubject(request, response);  
		    if(!subject.isAuthenticated() && !subject.isRemembered()){ 
		        
		        return executeLogin(request, response);
		    }
            return true;
        } else {
            
            return true;
        }
    }
	
    @Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		
    	this.issueSuccessRedirect(request, response);
        //we handled the success redirect directly, prevent the chain from continuing:
        return false;
	}
    

	@Override
	protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
		SavedRequest save = WebUtils.getSavedRequest(request);
		if(save != null){
			WebUtils.redirectToSavedRequest(request, response, save.getRequestUrl());
		}else{
			WebUtils.redirectToSavedRequest(request, response, getSuccessUrl());
		}
	}

	/** 
     * 验证码校验
     */  
	@Override
    protected boolean executeLogin(ServletRequest request,ServletResponse response) throws Exception {  
		
    	HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        CaptchaUsernamePasswordToken token = null;
        try {  
        	
        	token = createToken(request, response); 
            String captcha = (String) httpServletRequest.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
            if (captcha != null && !captcha.equalsIgnoreCase(token.getCaptcha())) {
            	
            	throw new AuthenticationException();
            }
            Subject subject = getSubject(request, response);  
            subject.login(token);//正常验证  
            
//            SessionUtils.setSessionTimeOut(token.isRememberMe());
            LOG.info("sign in success");  
            return this.onLoginSuccess(token, subject, request, response);  
        }catch (AuthenticationException e) {
        	LOG.error("sign in failed exception is {}",e);  
            return onLoginFailure(token, e, request, response);  
        }  
    }
    
    @Override  
    protected CaptchaUsernamePasswordToken createToken(ServletRequest request,ServletResponse response){  
        String username = getUsername(request);  
        String password = getPassword(request);  
        String captcha = getCaptcha(request);  
        boolean rememberMe = isRememberMe(request);  
        String host = getHost(request);  
        
        if(StringUtils.isEmpty(username)){
        	
        	throw new UsernameConnotBeEmptyException();
        }else{
        	
        	LOG.error("sign in user is {}",username);
        	((HttpServletRequest) request).setAttribute(Constants.USERNAME_Attribute_KEY, username);
        }
        if(StringUtils.isEmpty(password)){
        	
        	throw new PasswordConnotBeEmptyException();
        }else if(StringUtils.isEmpty(captcha)){
        	
        	throw new CaptchaConnotBeEmptyException();
        }
        
        return new CaptchaUsernamePasswordToken(username,password.toCharArray(), rememberMe, host, captcha);  
    }
    
    public static final String DEFAULT_CAPTCHA_PARAM = "captcha";  
    
    private String captchaParam = DEFAULT_CAPTCHA_PARAM;  
    
    public static final String DEFAULT_ERROR_KEY_ATTRIBUTE_NAME = "shiroLoginFailure";

    public static final String DEFAULT_USERNAME_PARAM = "username";
    public static final String DEFAULT_PASSWORD_PARAM = "password ";
    public static final String DEFAULT_REMEMBER_ME_PARAM = "rememberMe";
    
    private String usernameParam = DEFAULT_USERNAME_PARAM;
    private String passwordParam = DEFAULT_PASSWORD_PARAM;
    private String rememberMeParam = DEFAULT_REMEMBER_ME_PARAM;

    private String failureKeyAttribute = DEFAULT_ERROR_KEY_ATTRIBUTE_NAME;
    
	public String getCaptchaParam() {  
        return captchaParam;  
    }  
  
    public void setCaptchaParam(String captchaParam) {  
        this.captchaParam = captchaParam;  
    }  
  
    protected String getCaptcha(ServletRequest request) {  
        return WebUtils.getCleanParam(request, getCaptchaParam());  
    }

	public String getUsernameParam() {
		return usernameParam;
	}

	public void setUsernameParam(String usernameParam) {
		this.usernameParam = usernameParam;
	}

	public String getPasswordParam() {
		return passwordParam;
	}

	public void setPasswordParam(String passwordParam) {
		this.passwordParam = passwordParam;
	}

	public String getRememberMeParam() {
		return rememberMeParam;
	}

	public void setRememberMeParam(String rememberMeParam) {
		this.rememberMeParam = rememberMeParam;
	}

	public String getFailureKeyAttribute() {
		return failureKeyAttribute;
	}

	public void setFailureKeyAttribute(String failureKeyAttribute) {
		this.failureKeyAttribute = failureKeyAttribute;
	}  
    
}
