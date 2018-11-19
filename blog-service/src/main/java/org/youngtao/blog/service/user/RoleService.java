package org.youngtao.blog.service.user;

import java.util.List;

import org.youngtao.blog.data.domain.user.Role;

/**
 * 
 * @author linyantao
 * @date 2017年5月21日
 */
public interface RoleService {

    /**
     * 根据角色名分页查询角色信息
     * @param roleName 角色名
     * @param start 开始记录
     * @param end 条数
     * @return
     */
    List<Role> findRoleByPage(String roleName,int start,int end);
    
    /**
     * 根据角色名查询总记录数
     * @param roleName 角色名
     * @return
     */
    int findTotalRoleByPage(String roleName);
}
