package org.youngtao.blog.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.youngtao.blog.data.domain.user.Permissions;
import org.youngtao.blog.data.domain.user.User;
import org.youngtao.blog.permission.session.SessionUtils;
import org.youngtao.blog.service.user.PermissionsService;

@Controller
@RequestMapping("/permissions")
public class PermissionsController {

	@Autowired
	private PermissionsService permissionsService;
	
	@RequestMapping(value = "/findPermissions",method = RequestMethod.GET)
	@ResponseBody
	public List<Permissions> findPermissions() throws Exception{

		User user = SessionUtils.getUserSession();
		
		List<Permissions> permissionsList = permissionsService.findTreePermissionsByUserId(user.getId());
		
		return permissionsList;
	}
}
