package org.youngtao.blog.log;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author linyantao
 * @date 2017年5月21日
 * AOP 输出请求报文、返回报文
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MessageLog {
    
    /**
     * 是否输出请求报文日志，默认输出
     * @return
     */
    boolean requestOutput() default true;
    
    /**
     * 是否输出输出返回报文日志，默认输出
     * @return
     */
    boolean responseOutput() default true;
    
    String desc() default "";
}
