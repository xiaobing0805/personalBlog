package org.youngtao.blog.permission.session;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.youngtao.blog.common.utils.AjaxUtils;
import org.youngtao.blog.data.domain.user.User;
import org.youngtao.blog.data.domain.user.UserSettings;
import org.youngtao.blog.service.user.UserService;
import org.youngtao.blog.service.user.UserSettingsService;

public class UserSessionFillter extends AccessControlFilter{

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserSettingsService userSettingsService;
	
	@Override  
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
         
        Subject subject = getSubject(request, response);
        if (subject == null) {  
            return false;  
        }
        if(subject.isAuthenticated() || subject.isRemembered()){ 
        	if(SessionUtils.sessionTimeout((HttpServletRequest) request)){

        		Object principal = subject.getPrincipal();  
                if(principal != null){

                    String username = (String) principal;
                	User user = userService.getUserByUsername(username);
                    List<UserSettings> us = userSettingsService.findByUserId(user.getId());
                    SessionUtils.setUserSession(user); //将用户信息Session
                    SessionUtils.cacheUserSettings(us); //将用户配置信息缓存进session
                    
                    //更新用户sessionId
                    userService.updateSessionId(user.getId(), subject.getSession().getId().toString());
                }
        	}
        }else{

        	if(AjaxUtils.isAjax((HttpServletRequest) request)){

            	HttpServletResponse httpResponse = (HttpServletResponse) response; 
            	httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            	return false;
        	}
        }
        return true;  
    }  
	
	
	
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		return true;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		return true;
	}

}
