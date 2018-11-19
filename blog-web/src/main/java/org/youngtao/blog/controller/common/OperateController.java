package org.youngtao.blog.controller.common;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.youngtao.blog.common.constant.Constants;
import org.youngtao.blog.data.domain.user.UserSettings;
import org.youngtao.blog.permission.session.SessionUtils;

@Controller
public class OperateController {
	

	@RequestMapping(value="/operate/**")
	public ModelAndView operatePlatform(){
		
		ModelAndView mv = new ModelAndView();
		List<UserSettings> us = SessionUtils.getCacheUserSettings();
		for (UserSettings userSettings : us) {
			if(Constants.SIDEBAR_TOGGLE_EVEN.equals(userSettings.getKey())){

				mv.addObject("sidebarToggle", userSettings.getValue());
				break;
			}
		}
        mv.setViewName("/index");
        return mv;
	}
	
	@RequestMapping(value="/getBodyHtml",method=RequestMethod.GET)
	public ModelAndView getBodyHtml(String htmlPath){
		
		ModelAndView mv = new ModelAndView();
        
        mv.setViewName(htmlPath);
        return mv;
	}
}
