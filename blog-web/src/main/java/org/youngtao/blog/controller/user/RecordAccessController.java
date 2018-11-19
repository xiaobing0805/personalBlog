package org.youngtao.blog.controller.user;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.youngtao.blog.service.user.RecordAccessService;

@Controller
@RequestMapping("/recordAccess")
public class RecordAccessController {

	@Autowired  
    private SessionDAO sessionDAO; 
	
	@Autowired
	private RecordAccessService recordAccessService;
	
	@RequestMapping(value="/getCurrentOnline",method=RequestMethod.GET)
	@ResponseBody
	public int getCurrentOnline(HttpServletRequest request,HttpServletResponse response){
		Collection<Session> sessions =  sessionDAO.getActiveSessions();
		return sessions.size();
	}
	
	@RequestMapping(value="/getTotalOnline",method=RequestMethod.GET)
	@ResponseBody
	public int getTotalOnline(HttpServletRequest request,HttpServletResponse response){
		
		int totalOnline = recordAccessService.getTotalOnline();
		return totalOnline;
	}
}
