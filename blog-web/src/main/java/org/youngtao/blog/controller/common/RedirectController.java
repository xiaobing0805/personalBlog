package org.youngtao.blog.controller.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.youngtao.blog.common.constant.Constants;
import org.youngtao.blog.permission.session.SessionUtils;

@Controller
@RequestMapping("redirect")
public class RedirectController {

    @Autowired
    private MessageSource messageSource;
    
    @Autowired 
    private CookieLocaleResolver resolver;
	
	@RequestMapping("/login")  
    public ModelAndView login(HttpServletRequest request,String kickout) throws Exception {

        ModelAndView mv = new ModelAndView();
        
        if(SessionUtils.sessionTimeout(request)){
        	
        	if(!StringUtils.isEmpty(kickout) && kickout.equals(Constants.KILL_OUT)){
        		mv.addObject(Constants.ERROR, messageSource.getMessage("business.killout", null, resolver.resolveLocale(request)));
        	}
        	String forceLogout = request.getParameter("forceLogout");
        	if(!StringUtils.isEmpty(forceLogout) && forceLogout.equals(Constants.FORCE_LOGOUT_USER)){
        		mv.addObject(Constants.ERROR, messageSource.getMessage("business.forcelogout", null, resolver.resolveLocale(request)));
        	}
        	mv.setViewName("auth/login");
        }else{
        	mv.setViewName("/public/blog");
        }
        return mv;
    }
}
