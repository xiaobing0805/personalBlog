package org.youngtao.blog.log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;
import org.youngtao.blog.common.utils.LogUtils;

import com.alibaba.fastjson.JSON;

/**
 * 
 * @author linyantao
 * @date 2017年5月21日
 */
@Aspect
@Repository
public class LogAspect {

    private static final Logger LOG = LogUtils.get();

    /**
     * 定义一个方法，用于声明切面表达式，一般地，该方法中不再需要添加其他的代码
     * 
     */
    @Pointcut("@annotation(org.youngtao.blog.log.MessageLog)")
    public void declareJoinPoint() {
    }

    @Before("declareJoinPoint() && @annotation(mo)")
    public void before(JoinPoint point,MessageLog mo) throws IOException {
        if(mo.requestOutput()){

            List<Object> params = new ArrayList<Object>();
            Object[] requestParams = point.getArgs();
            for (Object object : requestParams) {
                if(!(object instanceof BindingResult)){
                    
                    params.add(object);
                }
            }
            LOG.info("{} request params is {}",(point.getTarget().getClass().getName() + "." + point.getSignature().getName() + "()"),JSON.toJSONString(params));
        }
    }

    @AfterReturning(value = "declareJoinPoint() && @annotation(mo)", returning = "resultValue")
    public void after(JoinPoint point, Object resultValue,MessageLog mo) throws IOException {
        if(mo.responseOutput()){

            LOG.info("{} response is {}",(point.getTarget().getClass().getName() + "." + point.getSignature().getName() + "()"),JSON.toJSONString(resultValue));
        }
    }
}
