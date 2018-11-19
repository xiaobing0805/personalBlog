package org.youngtao.blog.data.mapper.user;

import java.util.List;

import org.youngtao.blog.data.domain.user.UserSettings;

public interface UserSettingsMapper {

	/**
	 * 根据用户ID查询用户配置信息
	 * @param userId
	 * @return
	 */
	List<UserSettings> findByUserId(String userId);
}
