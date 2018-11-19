package org.youngtao.blog.permission.session;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.youngtao.blog.common.constant.Constants;
import org.youngtao.blog.data.domain.user.User;
import org.youngtao.blog.data.domain.user.UserSettings;

/**
 * 
 * @author linyantao
 * @date 2017年3月14日
 */
public class SessionUtils {
	
	/**
	 * 缓存用户配置信息
	 */
	public static void cacheUserSettings(List<UserSettings> us){
		
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		session.setAttribute(Constants.USER_SETTINGS_INFO, us);
	}
	
	/**
	 * 获取用户配置信息
	 * @return
	 */
	public static List<UserSettings> getCacheUserSettings(){
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		@SuppressWarnings("unchecked")
        List<UserSettings> us = (List<UserSettings>) session.getAttribute(Constants.USER_SETTINGS_INFO);
		return us;
	}
	
	/**
	 * 把用户信息存到SESSION中
	 * @param obj
	 */
	public static void setUserSession(Object obj){
		
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		session.setAttribute(Constants.USER_SESSION_INFO, obj);
	}
	
	/**
	 * 设置SESSION的生命周期
	 * @param remenberMe
	 */
	public static void setSessionTimeOut(Boolean remenberMe){
		
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		if(remenberMe){
			session.setTimeout(Constants.SESSION_TIME_OUT_30_DAY);
		}else{
			session.setTimeout(Constants.SESSION_TIME_OUT_30_MIN);
		}
	}
	
	/**
	 * 清除登录数据
	 */
	public static void removeUserSession(){
		
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession(false);
		session.removeAttribute(Constants.USER_SESSION_INFO);
	}
	
	/**
	 * 从SESSION中取出用户信息
	 * @return
	 * @throws IOException 
	 */
	public static User getUserSession(){
		
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		User user = (User) session.getAttribute(Constants.USER_SESSION_INFO);
		return user;
	}
	
	public static boolean sessionTimeout(HttpServletRequest request){
		
		HttpSession session = request.getSession();  
        User current_user = (User) session.getAttribute(Constants.USER_SESSION_INFO);  
        //判断session是否失效，若失效刷新之  
        if(current_user == null){  
        	
        	 return true;
        }
		return false;
	}
}
