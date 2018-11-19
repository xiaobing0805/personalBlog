package org.youngtao.blog.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.youngtao.blog.common.exception.BusinessException;
import org.youngtao.blog.data.mapper.user.RecordAccessMapper;
import org.youngtao.blog.service.user.RecordAccessService;

@Service
public class RecordAccessServiceImpl implements RecordAccessService {

	@Autowired
	private RecordAccessMapper recordAccessMapper;
	
	@Override
	public void addRecordAccess(String sessionId) throws BusinessException{
		
		int count = recordAccessMapper.addRecordAccess(sessionId);
		if(count <= 0){
			
			throw new BusinessException("business.save.failed");
		}
	}

	@Override
	public int getTotalOnline() {
		
		return recordAccessMapper.getTotalOnline();
	}

}
