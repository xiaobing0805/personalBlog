package org.youngtao.blog.permission.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.youngtao.blog.common.constant.Constants;
import org.youngtao.blog.permission.exception.CaptchaConnotBeEmptyException;
import org.youngtao.blog.permission.exception.PasswordConnotBeEmptyException;
import org.youngtao.blog.permission.exception.UsernameConnotBeEmptyException;

@Controller
public class LoginFromController {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired 
	private CookieLocaleResolver resolver;
	
	@RequestMapping(value = "/login",method = RequestMethod.POST)  
    public ModelAndView login(HttpServletRequest request) throws Exception {

        String exceptionClassName = (String) request.getAttribute(Constants.SHIRO_LOGIN_FAILURE);
        ModelAndView mv = new ModelAndView();
        if (exceptionClassName != null) {
        	
        	if(UsernameConnotBeEmptyException.class.getName().equals(exceptionClassName)){
        		
        		mv.addObject(Constants.ERROR, messageSource.getMessage("username.connot.be.empty", null, resolver.resolveLocale(request)));
        	}else if(PasswordConnotBeEmptyException.class.getName().equals(exceptionClassName)){
        	
        		mv.addObject(Constants.ERROR, messageSource.getMessage("password.connot.be.empty", null, resolver.resolveLocale(request)));
        	}else if(CaptchaConnotBeEmptyException.class.getName().equals(exceptionClassName)){
        		
        		mv.addObject(Constants.ERROR, messageSource.getMessage("captcha.connot.be.empty", null, resolver.resolveLocale(request)));
        	}else if(AccountException.class.getName().equals(exceptionClassName)){
        		
        		mv.addObject(Constants.ERROR, messageSource.getMessage("account.does.not.exist", null, resolver.resolveLocale(request)));
        	}else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
                 
                mv.addObject(Constants.ERROR, messageSource.getMessage("username.or.password.incorrect", null, resolver.resolveLocale(request)));
            }else if(AuthenticationException.class.getName().equals(exceptionClassName)){
                
                mv.addObject(Constants.ERROR, messageSource.getMessage("captcha.error", null, resolver.resolveLocale(request)));
            }else if(UnknownAccountException.class.getName().equals(exceptionClassName)){
        		
        		mv.addObject(Constants.ERROR, messageSource.getMessage("system.exception", null, resolver.resolveLocale(request)));
        	}else if(ExcessiveAttemptsException.class.getName().equals(exceptionClassName)){
        		
        		mv.addObject(Constants.ERROR, messageSource.getMessage("business.abnormal.number", null, resolver.resolveLocale(request)));
        	}else{
            	
                throw new Exception();// 最终在异常处理器生成未知错误 
            }
		}
        mv.setViewName("auth/login");
        return mv;
    }
	
}
