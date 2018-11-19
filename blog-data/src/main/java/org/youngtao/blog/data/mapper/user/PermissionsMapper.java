package org.youngtao.blog.data.mapper.user;

import java.util.List;

import org.youngtao.blog.data.domain.user.Permissions;

public interface PermissionsMapper {
	
	/**
	 * 根据userid获取该用户所有权限信息
	 * @param parentId
	 * @return
	 */
	List<Permissions> findPermissionsByUserId(String userId);
}
