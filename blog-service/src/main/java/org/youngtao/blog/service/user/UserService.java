package org.youngtao.blog.service.user;

import java.util.List;

import org.youngtao.blog.common.exception.BusinessException;
import org.youngtao.blog.data.domain.user.User;

public interface UserService {

	/**
	 * 根据用户名获取用户信息
	 * @param username
	 * @return
	 */
	User getUserByUsername(String username);
	
	/**
	 * 根据登录名分页查询用户信息
	 * @param loginName
	 * @return
	 */
	List<User> findUserByPage(String loginName,int start,int end);
	
	/**
	 * 根据登录名获取数据总数
	 * @param loginName
	 * @return
	 */
	int findUserTotalByPage(String loginName);
	
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	void addUser(User user) throws BusinessException;
	
	/**
	 * 根据主键修改用户信息
	 * @param user
	 * @throws BusinessException
	 */
	void updateUser(User user) throws BusinessException;
	
	/**
	 * 根据主键修改用户会话ID
	 * @param id
	 * @param sessionId
	 * @return
	 */
	void updateSessionId(String id,String sessionId) throws BusinessException;
	
	/**
	 * 根据登录账号批量修改用户密码
	 * @param loginName
	 * @param pwd
	 * @return
	 */
	void updateUserPwd(String[] loginName,String pwd,String operator,String salt) throws BusinessException;
	
	/**
	 * 批量禁用用户
	 * @param loginName
	 * @throws BusinessException
	 */
	void updateDisableStatus(String[] loginName,String operator,int isDisable) throws BusinessException;
}
