package org.youngtao.blog.data.mapper.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.youngtao.blog.data.domain.user.User;

public interface UserMapper {

	/**
	 * 根据用户名查询用户信息
	 * @param username
	 * @return
	 */
	User getByUsername(String username);
	
	/**
	 * 根据登录名分页查询用户信息
	 * @param loginName
	 * @return
	 */
	List<User> findUserByPage(@Param("loginName")String loginName,@Param("start")int start,@Param("end")int end);
	
	/**
	 * 根据登录名获取数据总数
	 * @param loginName
	 * @return
	 */
	int findUserTotalByPage(@Param("loginName")String loginName);
	
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	int addUser(User user);
	
	/**
	 * 根据主键修改用户
	 * @param user
	 * @return
	 */
	int updateUser(User user);
	
	/**
	 * 根据主键修改用户会话ID
	 * @param id
	 * @param sessionId
	 * @return
	 */
	int updateSessionId(@Param("id")String id,@Param("sessionId")String sessionId);
	
	/**
	 * 根据登录账号批量修改用户密码
	 * @param loginName
	 * @param pwd
	 * @return
	 */
	int updateUserPwd(@Param("loginName")String[] loginName,@Param("pwd")String pwd,@Param("operator")String operator,@Param("salt")String salt);
	
	/**
	 * 批量禁用用户
	 * @param loginName
	 * @return 
	 * @throws BusinessException
	 */
	int updateDisableStatus(@Param("loginName")String[] loginName,@Param("operator")String operator,@Param("isDisable")int isDisable);
}
