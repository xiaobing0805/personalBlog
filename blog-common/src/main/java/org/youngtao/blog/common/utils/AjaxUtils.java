package org.youngtao.blog.common.utils;

import javax.servlet.http.HttpServletRequest;

public class AjaxUtils {

	/**
	 * 判断请求是否是Ajax请求
	 * @param request
	 * @return
	 */
	public static boolean isAjax(HttpServletRequest request){
		String requestSource = request.getHeader("X-Requested-With");
		if(null != requestSource && "XMLHttpRequest".equals(requestSource)){
			return true;
		}
		return false;
	}
}
