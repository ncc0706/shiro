package com.xlinyu.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

public class LoginFilter extends FormAuthenticationFilter {

	@Override
	protected void issueSuccessRedirect(ServletRequest request,
			ServletResponse response) throws Exception {
		if(isAjaxRequest(request)){
			//PrintWriter writer = getWriterFromResponse(response);
			
		}else{
			WebUtils.redirectToSavedRequest(request, response, getSuccessUrl());
		}
	}

	@Override
	protected boolean onLoginFailure(AuthenticationToken token,
			AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		return super.onLoginFailure(token, e, request, response);
	}
	
	/**
	 * 是否是ajax请求
	 * @param request
	 * @return
	 */
	public boolean isAjaxRequest(ServletRequest request) {
		return ((HttpServletRequest) request).getHeader("x-requested-with") != null;
	}
	
	public PrintWriter getWriterFromResponse(ServletResponse res){
		HttpServletResponse response = (HttpServletResponse)res;
		response.setHeader("Content-type", "text/html;charset=UTF-8");  
		
		response.setCharacterEncoding("UTF-8");  
		try {
			return response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
