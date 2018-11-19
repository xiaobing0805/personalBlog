package org.youngtao.blog.data.mapper.user;

public interface RecordAccessMapper {

	/**
	 * 新增访问记录
	 * @param sessionId
	 * @return
	 */
	int addRecordAccess(String sessionId);
	
	/**
	 * 获取所有访问人数
	 * @return
	 */
	int getTotalOnline();
}
