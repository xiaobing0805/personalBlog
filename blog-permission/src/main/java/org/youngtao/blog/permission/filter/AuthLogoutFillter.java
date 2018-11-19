package org.youngtao.blog.permission.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.util.WebUtils;

import com.alibaba.dubbo.common.utils.StringUtils;

public class AuthLogoutFillter extends LogoutFilter{

	@Override
	protected void issueRedirect(ServletRequest request, ServletResponse response, String redirectUrl)
			throws Exception {
		
		String backstageUrl = ((HttpServletRequest) request).getParameter("backstageUrl");
		
		if(!StringUtils.isEmpty(backstageUrl)){
			WebUtils.issueRedirect(request, response, backstageUrl);
		}else{
			WebUtils.issueRedirect(request, response, redirectUrl);
		}
	}

	
}
