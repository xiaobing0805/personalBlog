package org.youngtao.blog.controller.user;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.youngtao.blog.common.domain.Page;
import org.youngtao.blog.controller.request.user.RoleRequest;
import org.youngtao.blog.data.domain.user.Role;
import org.youngtao.blog.log.MessageLog;
import org.youngtao.blog.service.user.RoleService;
import org.youngtao.blog.validation.ParamsValidate;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired  
    private RoleService roleService; 

    @RequestMapping(value = "/findRoleByPage",method = RequestMethod.GET)
    @ResponseBody
    @ParamsValidate
    @MessageLog()
    @RequiresPermissions("role:find")
    public Page<Role> findRoleByPage(RoleRequest request) throws Exception{
        
        Page<Role> result = new Page<Role>(request.getCurrentPage(),request.getPageSize());
        
        List<Role> userList = roleService.findRoleByPage(request.getName(),result.getStartRecord(),result.getPageSize());
        int total = roleService.findTotalRoleByPage(request.getName());
        
        result.setTotalRecord(total);
        result.setDatas(userList);
        
        return result;
    }
}
