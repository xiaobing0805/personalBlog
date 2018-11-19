package org.youngtao.blog.service.user.impl;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.youngtao.blog.common.exception.BusinessException;
import org.youngtao.blog.common.utils.LogUtils;
import org.youngtao.blog.data.domain.user.User;
import org.youngtao.blog.data.mapper.user.UserMapper;
import org.youngtao.blog.service.user.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private static Logger LOG = LogUtils.get();

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User getUserByUsername(String username) {
		
		return userMapper.getByUsername(username);
	}

	@Override
	public List<User> findUserByPage(String loginName,int start,int end) {
		
		return userMapper.findUserByPage(loginName, start, end);
	}

	@Override
	public int findUserTotalByPage(String loginName) {
		
		return userMapper.findUserTotalByPage(loginName);
	}

	@Override
	public void addUser(User user) throws BusinessException{
		
		User u = userMapper.getByUsername(user.getLoginName());
		if(u != null){
			
			LOG.error("add user {} is exits",user.getLoginName());
			throw new BusinessException("business.username.exits");
		}
		int count = userMapper.addUser(user);
		if(count <= 0){
			
			LOG.error("add user failed");
			throw new BusinessException("business.save.failed");
		}
	}

	@Override
	public void updateUser(User user) throws BusinessException {
		int count = userMapper.updateUser(user);
		if(count <= 0){
			
			LOG.error("update user failed");
			throw new BusinessException("business.save.failed");
		}
	}

	@Override
	public void updateUserPwd(String[] loginName, String pwd, String operator,String salt) throws BusinessException {
		int count = userMapper.updateUserPwd(loginName, pwd, operator,salt);
		if(count <= 0){
		
			LOG.error("update user password failed");
			throw new BusinessException("business.update.failed");
		}
	}

	@Override
	public void updateDisableStatus(String[] loginName,String operator,int isDisable) throws BusinessException {
		int count = userMapper.updateDisableStatus(loginName,operator,isDisable);
		if(count <= 0){
			
			LOG.error("disable user password failed");
			throw new BusinessException("business.update.failed");
		}
	}

	@Override
	public void updateSessionId(String id, String sessionId) throws BusinessException{
		int count = userMapper.updateSessionId(id, sessionId);
		if(count <= 0){
			
			LOG.error("update user session id failed");
			throw new BusinessException("business.save.failed");
		}
	}

}
