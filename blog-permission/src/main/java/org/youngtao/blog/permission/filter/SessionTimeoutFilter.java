package org.youngtao.blog.permission.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

public class SessionTimeoutFilter extends FormAuthenticationFilter{

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		
//        Subject subject = getSubject(request, response);  
//        if(subject != null){
//
//        	Session session = subject.getSession(false);
//        	if(session.getAttribute(Constants.KICK_OUT_SESSION) != null){
//
//        		HttpServletRequest httpRequest = (HttpServletRequest) request;
//        		if(isAjax(httpRequest)){
//        			HttpServletResponse httpResponse = (HttpServletResponse) response; 
//            		httpResponse.sendError(Constants.KICK_OUT);
//            		return false;
//        		}
//        	}
//        }
//        boolean bool =isAjax(httpRequest);
//		if(bool){
//			
//			httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//		}
		return true;
	}

	public boolean isAjax(HttpServletRequest request){
		String requestSource = request.getHeader("X-Requested-With");
		if(null != requestSource && "XMLHttpRequest".equals(requestSource)){
			return true;
		}
		return false;
	}
	
}
