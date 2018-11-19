package org.youngtao.blog.permission.filter;

import java.io.IOException;
import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.youngtao.blog.common.constant.Constants;
import org.youngtao.blog.common.utils.AjaxUtils;
import org.youngtao.blog.common.utils.LogUtils;

public class KickoutSessionControlFilter extends AccessControlFilter {

	private static final Logger LOG = LogUtils.get();
	
	private String kickoutUrl; //踢出后到的地址
    private boolean kickoutAfter = false; //踢出之前登录的/之后登录的用户 默认踢出之前登录的用户
    private int maxSession = 1; //同一个帐号最大会话数 默认1

    private SessionManager sessionManager;
    private Cache<String, Deque<Serializable>> cache;

    public void setKickoutUrl(String kickoutUrl) {
        this.kickoutUrl = kickoutUrl;
    }

    public void setKickoutAfter(boolean kickoutAfter) {
        this.kickoutAfter = kickoutAfter;
    }

    public void setMaxSession(int maxSession) {
        this.maxSession = maxSession;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cache = cacheManager.getCache(Constants.SHIRO_KICKOUT_SESSION);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

    	Subject subject = getSubject(request, response);
      
        Session session = subject.getSession();  
        String username = (String) subject.getPrincipal();  
        Serializable sessionId = session.getId();  
      
        //TODO 同步控制  
        Deque<Serializable> deque = cache.get(username);  
        if(deque == null) {  
            deque = new LinkedList<Serializable>();  
            cache.put(username, deque);  
        }  
      
        //如果队列里没有此sessionId，且用户没有被踢出；放入队列  
        if(!deque.contains(sessionId) && session.getAttribute(Constants.KICK_OUT_SESSION) == null) {  
            deque.push(sessionId);  
        }  
        
        excessQuantityKickout(deque);
      
        //如果被踢出了，直接退出，重定向到踢出后的地址  
        if (session.getAttribute(Constants.KICK_OUT_SESSION) != null) {  
            
        	kickoutSession(request, response, subject);
            return false;  
        }  
        return true;
    }
    
    /**
     * 开始踢出会话
     * @param request
     * @param response
     * @throws IOException 
     */
    private void kickoutSession (ServletRequest request, ServletResponse response,Subject subject) throws IOException{
        try {  
        	
            subject.logout();  
        } catch (Exception e) {
        	
        	LOG.error("{} logout exception {}",subject.getPrincipal(),e);
        }  
        saveRequest(request);  
        if(AjaxUtils.isAjax((HttpServletRequest) request)){

            HttpServletResponse httpResponse = (HttpServletResponse) response; 
        	httpResponse.sendError(Constants.KICK_OUT);
        }else{

            WebUtils.issueRedirect(request, response, kickoutUrl);
        }  
    }
    
    /**
     * 如果队列里的sessionId数超出最大会话数，开始踢人 
     * @param deque 存储session会话缓存
     */
    private void excessQuantityKickout(Deque<Serializable> deque){

        while(deque.size() > maxSession) {  
            Serializable kickoutSessionId = null;  
            if(kickoutAfter) { //如果踢出后者  
                kickoutSessionId = deque.removeFirst();  
            } else { //否则踢出前者  
                kickoutSessionId = deque.removeLast();  
            }  
            try {  
                Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));  
                if(kickoutSession != null) {  
                    //设置会话的kickout属性表示踢出了  
                    kickoutSession.setAttribute(Constants.KICK_OUT_SESSION, true);  
                }  
            } catch (Exception e) { 
            	
            	LOG.error("excess quantity kickout exception {}",e);
            }  
        }  
    }

}
