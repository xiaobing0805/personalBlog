package org.youngtao.blog.permission.plugin.controlleradvice;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.youngtao.blog.common.domain.Result;
import org.youngtao.blog.common.exception.BusinessException;
import org.youngtao.blog.common.exception.ClientException;
import org.youngtao.blog.common.exception.ErrorCode;
import org.youngtao.blog.common.exception.ParamsException;
import org.youngtao.blog.common.exception.SystemException;
import org.youngtao.blog.common.utils.JsonResultUtils;
import org.youngtao.blog.common.utils.LogUtils;

/**
 * 统一异常拦截
 * @author Young tao
 *
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	
	private static Logger LOG = LogUtils.get();
	
	@Autowired 
	private CookieLocaleResolver resolver;
	
	@Autowired
	private MessageSource messageSource;
	
    @ExceptionHandler(Throwable.class)  
    @ResponseBody
    public Result<?> processUnauthenticatedException(Throwable ex,HttpServletRequest request) {  
         
         String errorCode = ErrorCode.SYSTEM_HAS_ERROR;
         String errorMssage = messageSource.getMessage("system.exception", null, resolver.resolveLocale(request));
         if(ex instanceof BusinessException){
        	 
        	 errorCode = ((BusinessException) ex).getErrorCode();
        	 errorMssage = messageSource.getMessage(((BusinessException) ex).getErrormessage(), null, resolver.resolveLocale(request));
         }else if(ex instanceof SystemException){
        	 
        	 errorCode = ((SystemException) ex).getErrorCode();
        	 errorMssage = messageSource.getMessage(((SystemException) ex).getErrormessage(), null, resolver.resolveLocale(request));
         }else if(ex instanceof ClientException){
        	 
        	 errorCode = ((ClientException) ex).getErrorCode();
        	 errorMssage = messageSource.getMessage(((ClientException) ex).getErrormessage(), null, resolver.resolveLocale(request));
         }else if(ex instanceof ParamsException){
        	 
        	 errorCode = ((ParamsException) ex).getErrorCode();
        	 errorMssage = ((ParamsException) ex).getErrormessage();
         }else if(ex instanceof UnauthorizedException){
             
             errorMssage = messageSource.getMessage("unauthorized.Exception", null, resolver.resolveLocale(request));
         }else{
             LOG.error("{} thows exception {}",request.getServletPath(),ex);
         }
         Result<?> result = JsonResultUtils.getJsonResult(null, false, errorCode, errorMssage);
         return result;
    }
}
