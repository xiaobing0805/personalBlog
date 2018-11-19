package org.youngtao.blog.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.youngtao.blog.builder.TreeBuilder;
import org.youngtao.blog.data.domain.user.Permissions;
import org.youngtao.blog.data.mapper.user.PermissionsMapper;
import org.youngtao.blog.service.user.PermissionsService;

@Service
public class PermissionsServiceImpl implements PermissionsService {

	@Autowired
	private PermissionsMapper permissionsMapper;

	@Override
	public List<Permissions> findTreePermissionsByUserId(String userId) {
		
	    List<Permissions> list = permissionsMapper.findPermissionsByUserId(userId);
	    
		return TreeBuilder.buildByRecursive(list);
	}

    @Override
    public List<Permissions> findPermissionsByUserId(String userId) {
       
        return permissionsMapper.findPermissionsByUserId(userId);
    }
	

}
