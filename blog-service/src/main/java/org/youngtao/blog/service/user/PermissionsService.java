package org.youngtao.blog.service.user;

import java.util.List;

import org.youngtao.blog.data.domain.user.Permissions;

public interface PermissionsService {

	/**
	 * 根据userid获取该用户所有权限信息
	 * @param userId 用户唯一标识
	 * @return
	 */
	List<Permissions> findTreePermissionsByUserId(String userId);
	
	/**
     * 根据userid获取该用户所有权限信息
     * @param userId 用户唯一标识
     * @return
     */
    List<Permissions> findPermissionsByUserId(String userId);
}
