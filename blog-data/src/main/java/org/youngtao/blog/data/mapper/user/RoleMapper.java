package org.youngtao.blog.data.mapper.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.youngtao.blog.data.domain.user.Role;

public interface RoleMapper {

	/**
	 * 根据用户主键获取所有角色
	 * @param userId
	 * @return
	 */
	List<Role> findRoleByUserId(String userId);
	
	/**
	 * 根据角色名分页查询角色信息
	 * @param roleName 角色名
	 * @param start 开始记录
	 * @param end 条数
	 * @return
	 */
    List<Role> findRoleByPage(@Param("roleName")String roleName,@Param("start")int start,@Param("end")int end);
    
    /**
     * 根据角色名查询总记录数
     * @param roleName 角色名
     * @return
     */
    int findTotalRoleByPage(String roleName);
}
