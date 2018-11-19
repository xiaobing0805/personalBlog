package org.youngtao.blog.permission.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.youngtao.blog.common.constant.Constants;
import org.youngtao.blog.common.exception.ErrorCode;
import org.youngtao.blog.common.exception.SystemException;
import org.youngtao.blog.permission.captcha.Captcha;

@Controller
@RequestMapping("/common")
public class CommonController {
	
	@RequestMapping(value="/redirect",method=RequestMethod.GET)
	@ResponseBody
	public String redirect() throws Exception{
		
		throw new SystemException(ErrorCode.BIZ_HAS_UNKNOW_ERROR,"business.save.failed");
	}
	
	@Autowired 
	private CookieLocaleResolver resolver;
	
	@RequestMapping(value="/lang",method=RequestMethod.GET)
	@ResponseBody
	public Boolean redirect(HttpServletRequest request,HttpServletResponse response,String lang){
		
    	if(!StringUtils.isEmpty(lang)){
            
            resolver.resolveLocale(request);
        	Locale locale = new Locale(lang);
            if(lang.equals("en")){
            	resolver.setLocale(request, response, locale);
            }else if(lang.equals("zh_TW")){
            	resolver.setLocale(request, response, locale);
            }else{
            	resolver.setLocale(request, response, locale);
            }
        }
        return true;
	}
	
	/**
     * 生成验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/captcha",method=RequestMethod.GET)
    public void validateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
    	response.setHeader("Cache-Control", "no-cache");
        String verifyCode = Captcha.generateTextCode(4);
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, verifyCode);
        response.setContentType("image/jpe"+ "g");
        BufferedImage bim = Captcha.generateImageCode(verifyCode, 108, 50, 0, true, Color.WHITE, null, null);
        ImageIO.write(bim, "JPEG", response.getOutputStream());
    }
}
