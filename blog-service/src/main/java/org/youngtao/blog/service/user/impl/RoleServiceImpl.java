package org.youngtao.blog.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.youngtao.blog.data.domain.user.Role;
import org.youngtao.blog.data.mapper.user.RoleMapper;
import org.youngtao.blog.service.user.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleMapper roleMapper;
    
    @Override
    public List<Role> findRoleByPage(String roleName, int start, int end) {
        
        return roleMapper.findRoleByPage(roleName, start, end);
    }

    @Override
    public int findTotalRoleByPage(String roleName) {
        
        return roleMapper.findTotalRoleByPage(roleName);
    }

    
}
