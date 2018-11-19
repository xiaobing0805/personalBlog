package org.youngtao.blog.permission.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.youngtao.blog.common.constant.Constants;
import org.youngtao.blog.common.utils.AjaxUtils;
import org.youngtao.blog.common.utils.LogUtils;

public class ForceLogoutFilter extends AccessControlFilter {
	
	private static final Logger LOG = LogUtils.get();

	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {  
        Session session = getSubject(request, response).getSession(false);  
        if(session == null) {  
            return true;  
        }  
        return session.getAttribute(Constants.SESSION_FORCE_LOGOUT_KEY) == null;  
    }  
	
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {  
        try {  
        	
        	Subject subject = getSubject(request, response); 
        	subject.logout(); //强制退出
        } catch (Exception e) {
        	
        	LOG.error("force logout user exception {}",e);
        }  
        if(AjaxUtils.isAjax((HttpServletRequest) request)){

            HttpServletResponse httpResponse = (HttpServletResponse) response; 
        	httpResponse.sendError(Constants.FORCE_LOGOUT);
        }else{

            String forceLogout = getLoginUrl() + (getLoginUrl().contains("?") ? "&" : "?") + "forceLogout=1";  
            WebUtils.issueRedirect(request, response, forceLogout); 
        }   
        return false;  
    }
}
