package org.youngtao.blog.permission.listen;

import java.io.Serializable;
import java.util.Deque;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.youngtao.blog.common.constant.Constants;
import org.youngtao.blog.common.utils.LogUtils;
import org.youngtao.blog.data.domain.user.User;
import org.youngtao.blog.service.user.RecordAccessService;
import org.youngtao.blog.service.user.UserService;

public class UserSessionListener implements SessionListener{

	private static Logger LOG = LogUtils.get();
	
	@Autowired
	private CacheManager cacheManager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RecordAccessService recordAccessService;
	
	@Override
	public void onStart(Session session) {
		try {
			
			if(null != session.getId()){

				LOG.info(session.getId().toString());
				recordAccessService.addRecordAccess(session.getId().toString());
			}
		} catch (Exception e) {
			
			LOG.error("add record access failed exception {}",e);
		}
	}

	@Override
	public void onStop(Session session) {
		
		destroyCacheSession(session);
	}

	@Override
	public void onExpiration(Session session) {
		
		destroyCacheSession(session);
	}

	public void destroyCacheSession(Session session){
		
		try {
			
			User user = (User) session.getAttribute(Constants.USER_SESSION_INFO);
			if(user != null){

				Cache<String, Deque<Serializable>> cache = cacheManager.getCache(Constants.SHIRO_KICKOUT_SESSION);
				Deque<Serializable> deque = cache.get(user.getLoginName());
				if(deque != null){
					deque.remove(session.getId());
				}
				
				//更新用户sessionId
				if(session.getAttribute(Constants.KICK_OUT_SESSION) == null){
	                userService.updateSessionId(user.getId(), "");
				}
			}
		} catch (Exception e) {
			LOG.error("destroy cache session exception {}",e);
		}
	}
}
