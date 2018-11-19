package org.youngtao.blog.validation;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.youngtao.blog.common.exception.ErrorCode;
import org.youngtao.blog.common.exception.ParamsException;
import org.youngtao.blog.common.utils.LogUtils;
import org.youngtao.blog.data.domain.user.User;
import org.youngtao.blog.permission.session.SessionUtils;

@Aspect
@Repository
public class ValidatorAspect {
	
    private static final Logger LOGGER = LogUtils.get();
    
    @Autowired
    private Validator validator;

    @Around("@annotation(org.youngtao.blog.validation.ParamsValidate)")
    public Object validate(ProceedingJoinPoint pjp) throws Throwable {

//    	RequestAttributes ra = RequestContextHolder.getRequestAttributes();
//      ServletRequestAttributes sra = (ServletRequestAttributes) ra;
//      HttpServletRequest request = sra.getRequest();
//      ServletResponse response = sra.getResponse();
        User user = SessionUtils.getUserSession();
        Object obj = null;
        try {
	        
	        Object[] o = pjp.getArgs();
	        for (int i = 0; i < o.length; i++) {
	        	
	            if(!(o[i] instanceof BindingResult)){

	                validate(o[i]);
	                validateGroups(o[i]);
	            }
	        }
        	
            obj = pjp.proceed();
        } catch (ParamsException e) {
        	
            LOGGER.error("{} params validate faild {}",user.getLoginName(),e.getErrormessage());
            throw e;
        } catch (Throwable e) {
        	
            LOGGER.error("{} params validate system exception : {}",user.getLoginName(), e);
            throw e;
        }

        return obj;
    }
    
    /**
     * 分组参数校验
     * @param o
     * @throws ParamsException
     */
    private void validateGroups(Object o) throws ParamsException{

        BindingResult bindingResult = null;
        
    	if(o instanceof BindingResult){
            bindingResult = (BindingResult) o;
        }
        
        if(bindingResult != null){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            if(fieldErrors.size() > 0){
                    
            	throw new ParamsException(ErrorCode.PARAMETER_HAS_ERROR, (fieldErrors.get(0).getField() + " " + fieldErrors.get(0).getDefaultMessage()));
            }
        }
    }
    
    /**
     * 正常参数校验
     * @param o
     * @throws ParamsException
     */
    private void validate(Object o) throws ParamsException {
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();  
//        Validator validator = factory.getValidator();  
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(o);
        if (constraintViolations.size() > 0) {
        	
            ConstraintViolation<Object> c = constraintViolations.iterator().next();
            throw new ParamsException(ErrorCode.PARAMETER_HAS_ERROR, (c.getPropertyPath() + " " + c.getMessage()));
        }
    }

}
