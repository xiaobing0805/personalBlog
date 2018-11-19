package org.youngtao.blog.controller.user;

import java.util.List;
import java.util.UUID;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.youngtao.blog.common.constant.Constants;
import org.youngtao.blog.common.domain.Page;
import org.youngtao.blog.common.exception.BusinessException;
import org.youngtao.blog.common.utils.ConvertUtils;
import org.youngtao.blog.common.utils.EncryptionUtils;
import org.youngtao.blog.common.utils.RandomUtils;
import org.youngtao.blog.controller.request.user.UserRequest;
import org.youngtao.blog.data.domain.user.User;
import org.youngtao.blog.log.MessageLog;
import org.youngtao.blog.permission.session.SessionUtils;
import org.youngtao.blog.service.user.UserService;
import org.youngtao.blog.validation.ParamsValidate;
import org.youngtao.blog.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired  
    private SessionDAO sessionDAO; 

	@RequestMapping(value = "/findUserByPage",method = RequestMethod.GET)
	@ResponseBody
    @MessageLog()
	@ParamsValidate
	@RequiresPermissions("user:find")
	public Page<User> findUserByPage(UserRequest request) throws Exception{
		
		Page<User> result = new Page<User>(request.getCurrentPage(),request.getPageSize());
		
		List<User> userList = userService.findUserByPage(request.getLoginName(),result.getStartRecord(),result.getPageSize());
		int total = userService.findUserTotalByPage(request.getLoginName());
		
		result.setTotalRecord(total);
		result.setDatas(userList);
		
		return result;
	}
	
	@RequestMapping(value = "/addUser",method = RequestMethod.POST)
	@ResponseBody
	@ParamsValidate
	@MessageLog()
	@RequiresPermissions("user:add")
	public Boolean addUser(@Validated({Valid.add.class}) UserRequest request,BindingResult bind) throws Exception{
		if(!request.getPassword().equals(request.getConfirmPassword())){
			
			throw new BusinessException("business.password.inconsistency");
		}
		
		User user = SessionUtils.getUserSession();
		
		String salt = RandomUtils.generateRandom(Constants.GENERATE_RANDOM_LENGTH);
		String pwd = EncryptionUtils.encryptionPwd(request.getPassword() + salt);
		
		request.setSalt(salt);
		request.setPassword(pwd);
		
		User u = ConvertUtils.convertObject(request, User.class);
		u.setId(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
		u.setCreator(user.getCreator());
		u.setOperator(user.getOperator());
		userService.addUser(u);
		return true;
	}
	
	@RequestMapping(value = "/updateUser",method = RequestMethod.POST)
	@ResponseBody
    @MessageLog()
	@ParamsValidate
	@RequiresPermissions("user:modify")
	public Boolean updateUser(@Validated({Valid.update.class}) UserRequest request,BindingResult bind) throws Exception{
		User user = SessionUtils.getUserSession();
		User u = ConvertUtils.convertObject(request, User.class);
		u.setOperator(user.getOperator());
		userService.updateUser(u);
		return true;
	}
	
	@RequestMapping(value = "/updateUserPwd",method = RequestMethod.POST)
	@ResponseBody
	@ParamsValidate
	@MessageLog()
	@RequiresPermissions("user:modifyPwd")
	public Boolean updateUserPwd(@Validated({Valid.updatePwd.class}) UserRequest request,@RequestParam("loginNames[]")String [] loginNames,BindingResult bind) throws Exception{
		User user = SessionUtils.getUserSession();
		if(loginNames.length <= 0){
			
			throw new BusinessException("business.loginname.number.must.be.greater.than");
		}else if(!request.getPassword().equals(request.getConfirmPassword())){
			
			throw new BusinessException("business.password.inconsistency");
		}
		String salt = RandomUtils.generateRandom(Constants.GENERATE_RANDOM_LENGTH);
		String pwd = EncryptionUtils.encryptionPwd(request.getPassword() + salt);
		userService.updateUserPwd(loginNames, pwd, user.getOperator(),salt);
		return true;
	}
	
	@RequestMapping(value = "/disableUser",method = RequestMethod.POST)
	@ResponseBody
	@MessageLog()
	@RequiresPermissions("user:disable")
	public Boolean disableUser(@RequestParam("loginNames[]")String [] loginNames) throws Exception{
		if(loginNames.length <= 0){
			
			throw new BusinessException("business.loginname.number.must.be.greater.than");
		}
		User user = SessionUtils.getUserSession();
		userService.updateDisableStatus(loginNames,user.getOperator(),Constants.IS_DISABLE);
		return true;
	}
	
	@RequestMapping(value = "/enableUser",method = RequestMethod.POST)
	@ResponseBody
	@MessageLog()
	@RequiresPermissions("user:enable")
	public Boolean enableUser(@RequestParam("loginNames[]")String [] loginNames) throws Exception{
		if(loginNames.length <= 0){
			
			throw new BusinessException("business.loginname.number.must.be.greater.than");
		}
		User user = SessionUtils.getUserSession();
		userService.updateDisableStatus(loginNames,user.getOperator(),Constants.IS_ENABLE);
		return true;
	}
	
	@RequestMapping("/updateSessionId")  
	@ResponseBody
	@MessageLog()
	@RequiresPermissions("user:offline")
    public Boolean updateSessionId(@RequestParam("loginNames[]")String [] loginNames) throws Exception {  
		
		for (int i = 0; i < loginNames.length; i++) {
			
			User user = userService.getUserByUsername(loginNames[i]);
			if(!StringUtils.isEmpty(user.getSessionId())){
				
				userService.updateSessionId(user.getId(), "");
			}
		}
        return true;  
    } 
	
	@RequestMapping("/forceLogout")  
	@ResponseBody
	@MessageLog()
	@RequiresPermissions("user:offline")
    public Boolean forceLogout(@RequestParam("sessionId[]") String[] sessionId) throws Exception {  
		for (int i = 0; i < sessionId.length; i++) {
			Session session = sessionDAO.readSession(sessionId[i]);  
	        if(session != null) {  
	        	
	            session.setAttribute(Constants.SESSION_FORCE_LOGOUT_KEY, Boolean.TRUE);  
	        } 
		}
        return true;  
    } 
	
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
		    System.out.println(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
        }
	}
}
