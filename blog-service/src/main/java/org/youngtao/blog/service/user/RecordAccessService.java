package org.youngtao.blog.service.user;

import org.youngtao.blog.common.exception.BusinessException;

public interface RecordAccessService {

	/**
	 * 新增访问记录
	 * @param sessionId
	 * @return
	 */
	void addRecordAccess(String sessionId) throws BusinessException;
	
	/**
	 * 获取所有访问人数
	 * @return
	 */
	int getTotalOnline();
}
