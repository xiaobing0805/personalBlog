package org.youngtao.blog.service.user;

import java.util.List;

import org.youngtao.blog.data.domain.user.UserSettings;

public interface UserSettingsService {

	/**
	 * 根据用户ID查询用户配置信息
	 * @param userId
	 * @return
	 */
	List<UserSettings> findByUserId(String userId);
}
