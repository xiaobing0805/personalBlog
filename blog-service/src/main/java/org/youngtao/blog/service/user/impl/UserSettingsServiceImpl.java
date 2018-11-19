package org.youngtao.blog.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.youngtao.blog.data.domain.user.UserSettings;
import org.youngtao.blog.data.mapper.user.UserSettingsMapper;
import org.youngtao.blog.service.user.UserSettingsService;

@Service
public class UserSettingsServiceImpl implements UserSettingsService {

	@Autowired
	private UserSettingsMapper userSettingsMapper;

	@Override
	public List<UserSettings> findByUserId(String userId) {
		
		return userSettingsMapper.findByUserId(userId);
	}
	
}
