package com.douzone.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter {
	
	//@Autowired
	//private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		ApplicationContext ac=WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		UserService userService = ac.getBean(UserService.class);
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
				
		UserVo userVo = userService.login(id,password);
		HttpSession session =  request.getSession(true);
				
		if(userVo == null) {
			session.setAttribute("result", "fail");
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
				
		// 로그인 처리
		session.setAttribute("loginuser", userVo);
		
		System.out.println(userVo);
		
		response.sendRedirect(request.getContextPath()+"/");
		
		return false;
	}

	
}
